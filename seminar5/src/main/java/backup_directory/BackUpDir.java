package backup_directory;

/*
Написать функцию, создающую резервную копию всех файлов
в директории(без поддиректорий) во вновь созданную папку ./backup
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class BackUpDir {
    public static void main(String[] args) throws IOException {
        File myDir = new File("seminar1");
        File myDir2 = new File(".");
        File myDir1 = new File("./seminar5");


        /*
        https://coderlessons.com/articles/java/raznitsa-mezhdu-getpath-getcanonicalpath-i-getabsolutepath-faila-v-java
        https://javarush.com/groups/posts/3636-kofe-breyk-99-kak-kopirovatjh-faylih-iz-odnogo-kataloga-v-drugoy-na-java-stroki-v-java
        https://javarush.com/groups/posts/2275-files-path
         */

/*
        System.out.println(myDir.getPath());            // .
        System.out.println(myDir.getAbsolutePath());    // D:\_GEEK Brains\Java\java_core\.
        System.out.println(myDir.getAbsoluteFile());    // D:\_GEEK Brains\Java\java_core\.
        System.out.println(myDir.getCanonicalPath());   // D:\_GEEK Brains\Java\java_core
        System.out.println(myDir.getCanonicalFile());   // D:\_GEEK Brains\Java\java_core
        System.out.println(myDir.getCanonicalFile().getName());   // java_core
        System.out.println(myDir.getName());            // .

        System.out.println(myDir.isDirectory());        // true
        System.out.println(myDir.isFile());             // false
        System.out.println(myDir.isAbsolute());         // false

        Path myDirPath = myDir.toPath();

        System.out.println("=====================");
        System.out.println(myDirPath);                  // .
        System.out.println(myDirPath.getParent());      // null
        System.out.println(myDirPath.getFileName());    // .
        System.out.println(myDirPath.getNameCount());   // 1
        System.out.println(myDirPath.getName(0)); // .
        System.out.println(myDirPath.getFileSystem());  // sun.nio.fs.WindowsFileSystem@6acbcfc0
        System.out.println(myDirPath.getRoot());        // null
        System.out.println(myDirPath.isAbsolute());     // false
        System.out.println(myDirPath.normalize());      // " "
        System.out.println(myDirPath.subpath(0,1));     // .
        System.out.println(myDirPath.toAbsolutePath()); // D:\_GEEK Brains\Java\java_core\.
        System.out.println(myDirPath.spliterator());    // java.util.Spliterators$IteratorSpliterator@3feba861
*/

        printTree(myDir);

        createBackup(myDir);

    }


    public static void createBackup(File srcDir) throws IOException {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");
            File srcPath = new File(srcDir.getCanonicalPath());
            Path backupPath = Paths.get(srcPath.toString() + "_backup_" + dateFormat.format(new Date()));
            System.out.println("\n<<  !  >>   Будет выполнено резервное копирование папки \n<<  !  >>   ИЗ " +
                    srcPath + "\n<<  !  >>   В " +
                    backupPath + "\n");


            if (!Files.exists(backupPath)) {
                Files.createDirectory(backupPath);
                System.out.println("Папка " + backupPath + " успешно создана. \n");
            } else {
                System.out.println("Папка " + backupPath + " уже существует и будет перезаписана. \n");
            }

            doBackup(srcPath, backupPath);
            System.out.println("\nКопирование завершено.");
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }


    public static void doBackup(File srcDir, Path backupPath) {
        try {
            int dirCapacity = srcDir.listFiles().length;
            for (int i = 0; i < dirCapacity; i++) {

                File fileOrDir = srcDir.listFiles()[i];


                if (fileOrDir.isDirectory()) {
                    Path newPath = Paths.get(backupPath + "//" + fileOrDir.getName());
                    if (!Files.exists(newPath)) {
                        Files.createDirectory(newPath);
                    } else {
                        System.out.println("Папка " + newPath + "уже существует");
                    }
                    doBackup(fileOrDir, newPath);
                }
                if (fileOrDir.isFile()) {
                    Path scrFile = Paths.get(fileOrDir.getCanonicalPath());
                    Path destFile = Paths.get(backupPath + "//" + fileOrDir.getName());
                    Files.copy(scrFile, destFile, REPLACE_EXISTING);
                    if (Files.exists(destFile)) {
                        System.out.printf("ФАЙЛ: %-14s ===>>> %-40s : SUCCESS %n",
                                scrFile.getFileName(),
                                destFile.subpath(4,destFile.getNameCount()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод-обёртка для рекурсии
     *
     * @param srcDir
     * @throws IOException
     */
    public static void printTree(File srcDir) throws IOException {
        if (srcDir.isDirectory()) {
            System.out.println("[" + srcDir.getCanonicalFile().getName() + "]"); // получить имя папки, даже если указано "."
            getTree(srcDir, "");
        } else
            System.out.println(srcDir.getName());

    }

    public static void getTree(File srcDir, String previousDirDivider) {
        int dirCapacity = srcDir.listFiles().length;
        for (int i = 0; i < dirCapacity; i++) {

            File fileOrDir = srcDir.listFiles()[i];

            if (fileOrDir.isFile()) {
                if (i < dirCapacity - 1) {
                    System.out.println(previousDirDivider + "├──" + fileOrDir.getName());
                } else {
                    System.out.println(previousDirDivider + "└──" + fileOrDir.getName());
                }
            }
            if (fileOrDir.isDirectory()) {
                if (i < dirCapacity - 1) {
                    System.out.println(previousDirDivider + "├──" + "[" + fileOrDir.getName() + "]");
                    previousDirDivider = previousDirDivider + "│  ";
                } else {
                    System.out.println(previousDirDivider + "└──" + "[" + fileOrDir.getName() + "]");
                    previousDirDivider = previousDirDivider + "   ";
                }
                getTree(fileOrDir, previousDirDivider);
                previousDirDivider = previousDirDivider.substring(0, previousDirDivider.length() - 3);
            }
        }
    }

}

/*
[seminar1]
├──[out]
│  ├──[pkgFunctions]
│  │  └──Maths.class
│  └──[pkgMain]
│     └──Calc.class
├──pom.xml
├──[src]
│  ├──[main]
│  │  ├──[java]
│  │  │  ├──[pkgFunctions]
│  │  │  │  └──Maths.java
│  │  │  └──[pkgMain]
│  │  │     └──Calc.java
│  │  └──[resources]
│  └──[test]
│     └──[java]
└──[target]
   ├──[classes]
   │  ├──[pkgFunctions]
   │  │  └──Maths.class
   │  └──[pkgMain]
   │     └──Calc.class
   └──[generated-sources]
      └──[annotations]

<<  !  >>   Будет выполнено резервное копирование папки
<<  !  >>   ИЗ D:\_GEEK Brains\Java\java_core\seminar1
<<  !  >>   В D:\_GEEK Brains\Java\java_core\seminar1_backup_24-02-10_01-46-21

Папка D:\_GEEK Brains\Java\java_core\seminar1_backup_24-02-10_01-46-21 успешно создана.

ФАЙЛ: Maths.class    ===>>> out\pkgFunctions\Maths.class             : SUCCESS
ФАЙЛ: Calc.class     ===>>> out\pkgMain\Calc.class                   : SUCCESS
ФАЙЛ: pom.xml        ===>>> pom.xml                                  : SUCCESS
ФАЙЛ: Maths.java     ===>>> src\main\java\pkgFunctions\Maths.java    : SUCCESS
ФАЙЛ: Calc.java      ===>>> src\main\java\pkgMain\Calc.java          : SUCCESS
ФАЙЛ: Maths.class    ===>>> target\classes\pkgFunctions\Maths.class  : SUCCESS
ФАЙЛ: Calc.class     ===>>> target\classes\pkgMain\Calc.class        : SUCCESS

Копирование завершено.

 */
