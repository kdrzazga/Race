package libs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassFinder {

    //from http://www.roseindia.net/java/java-get-example/get-classes-package.shtml
    public static List getClasseNamesInPackage(String jarName, String packageName) {
        ArrayList classesNames = new ArrayList();
        packageName = packageName.replaceAll("\\.", "/");

        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
            JarEntry jarEntry;
            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if ((jarEntry.getName().startsWith(packageName))
                        && (jarEntry.getName().endsWith(".class"))) {
                    classesNames.add(jarEntry.getName().replaceAll("/", "\\."));
                }
            }
        } catch (IOException e) {
        }
        return classesNames;
    }

    public static File getJarFile(Object obj) {

        CodeSource codeSource = obj.getClass().getProtectionDomain().getCodeSource();
        try {
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            return jarFile;
        } catch (URISyntaxException ex) {
            Logger.getLogger(ClassFinder.class.getName()).log(Level.SEVERE, null, ex);
            return new File("");
        }

    }
}
