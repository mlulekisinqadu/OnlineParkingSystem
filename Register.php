<?php
    require("password.php");

    $connect = mysqli_connect("localhost", "root", "", "eparking_db");
    
    $client_firstname = $_POST["client_firstname"];
    $client_lastname = $_POST["client_lastname"];
    $client_email = $_POST["client_email"];
    $client_phone = $_POST["client_phone"];    
    $client_password = $_POST["client_password"];
    $client_type = $_POST["client_type"];
    $client_depart = $_POST["client_depart"];

     function registerUser() {
        global $connect, $client_firstname, $client_lastname, $client_email, $client_phone,$client_password,$client_type,$client_depart;
        $passwordHash = password_hash($client_password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO client_user (client_firstname,client_lastname, client_email, client_phone, client_password, client_type, client_depart) VALUES (?, ?, ?, ?,?,?,?)");
        mysqli_stmt_bind_param($statement, "siss", $client_firstname, $client_lastname, $client_email,$client_phone,$passwordHash,$client_type,$client_depart);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }

    function usernameAvailable() {
        global $connect, $client_email;
        $statement = mysqli_prepare($connect, "SELECT * FROM client_user WHERE client_email = ?"); 
        mysqli_stmt_bind_param($statement, "s", $client_email);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }

    $response = array();
    $response["success"] = false;  

    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>
