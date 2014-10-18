package net.cubespace.devathlon14.util;

import net.cubespace.devathlon14.EffectPlugin;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by Fabian on 18.10.2014.
 */
public class ReflectionUtils {

    /**
     * Given a package name, attempts to reflect to find all classes within the package
     * on the local file system.
     *
     * @param packageName The package Name which should be scanned for Classes
     * @return A set of Classes which have been found
     */
    public static Set<Class> getClassesInPackage( String packageName ) {
        Set<Class> classes = new HashSet<>();
        String packageNameSlashed = "/" + packageName.replace( ".", "/" );

        // Get a File object for the package
        URL directoryURL = EffectPlugin.getInstance().getClass().getClassLoader().getResource( packageNameSlashed );
        if ( directoryURL == null ) {
            EffectPlugin.getInstance().getLogger().warning( "Could not retrieve URL resource: " + packageNameSlashed );
            return classes;
        }

        String directoryString = directoryURL.getFile();
        if ( directoryString == null ) {
            EffectPlugin.getInstance().getLogger().warning( "Could not find directory for URL resource: " + packageNameSlashed );
            return classes;
        }

        File directory = new File( directoryString );
        if ( directory.exists() ) {
            // Get the list of the files contained in the package
            String[] files = directory.list();
            for ( String fileName : files ) {
                // We are only interested in .class files
                if ( fileName.endsWith( ".class" ) ) {
                    // Remove the .class extension
                    fileName = fileName.substring( 0, fileName.length() - 6 );
                    try {
                        classes.add( Class.forName( packageName + "." + fileName ) );
                    } catch ( ClassNotFoundException e ) {
                        EffectPlugin.getInstance().getLogger().log( Level.WARNING, packageName + "." + fileName + " does not appear to be a valid class.", e );
                    }
                }
            }
        } else {
            EffectPlugin.getInstance().getLogger().warning( packageName + " does not appear to exist as a valid package on the file system." );
        }

        return classes;
    }

}
