    Import-Module PSSQLite

    $Database = "C:\serveurDB.db"
    $Query = "select * from serveurs"

Invoke-SqliteQuery -SQLiteConnection $C -Query "
        SELECT * FROM Patate"

javac E:\\cours\\Test\\EPSI_TDD_Restaurant\\src\TestPackage\\*.java

java -cp junit-jupiter-5.8.2.jar;. org.junit.runner.JUnitCore *.class