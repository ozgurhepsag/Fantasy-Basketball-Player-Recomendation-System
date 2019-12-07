<?php
/*class Database{
 
    // specify your own database credentials
    private $host = "localhost";
    private $db_name = "id11505355_data";
    private $username ="id11505355_data";
    private $password = "datadata";
    public $conn;
 
    // get the database connection
    public function getConnection(){
 
        $this->conn = null;
 
        try{
            $this->conn = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->db_name, $this->username, $this->password);
            $this->conn->exec("set names utf8");
            
        }catch(PDOException $exception){
            echo "Connection error: " . $exception->getMessage();
        }
 
        return $this->conn;
    }
} */
$conn = new mysqli('localhost','id11505355_data','datadata','id11505355_data');

?>