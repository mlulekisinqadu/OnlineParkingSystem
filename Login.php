<?php
    require("password.php");

    $con = mysqli_connect("localhost", "root", "", "eparking_db");
    
    $email = $_POST["email"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM client_user WHERE client_email = ?");
    mysqli_stmt_bind_param($statement, "s", $client_email);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $client_id, $client_firstname, $client_lastame,$client_email, $client_phone, $client_password,$client_type,$client_depart);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $client_password)) {
            $response["success"] = true;  
            $response["client_firstname"] = $client_firstname;
            $response["client_lastname"] = $client_lastname;
            $response["client_phone"] = $client_phone;
            $response["client_type"] = $clie_type;
            $response["client_depart"]=$client_depart;
        }
    }

    echo json_encode($response);
?>