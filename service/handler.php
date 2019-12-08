<?php
include_once 'database.php';

                    if($_SERVER['REQUEST_METHOD'] == 'GET'){
                        if(isset($_GET['tpm']) ) {
                        $tpm = $conn->real_escape_string($_GET['tpm']);
                        $sql = $conn->query("SELECT Player,TPM FROM Players GROUP BY TPM ORDER BY TPM DESC");
                        while($d = $sql->fetch_assoc())
                            $data[] = $d;
                        } 
                        else if(isset($_GET['pts']) ) {
                        $pts = $conn->real_escape_string($_GET['pts']);
                        $sql = $conn->query("SELECT Player,PTS FROM Players GROUP BY PTS ORDER BY PTS DESC");
                        while($d = $sql->fetch_assoc())
                            $data[] = $d;
                        }
                        else if(isset($_GET['ast']) ) {
                            $tpm = $conn->real_escape_string($_GET['ast']);
                            $sql = $conn->query("SELECT Player,AST FROM Players GROUP BY AST ORDER BY AST DESC");
                            while($d = $sql->fetch_assoc())
                                $data[] = $d;
                        } 
                        else if(isset($_GET['blk']) ) {
                            $tpm = $conn->real_escape_string($_GET['blk']);
                            $sql = $conn->query("SELECT Player,BLK FROM Players GROUP BY BLK ORDER BY BLK DESC");
                            while($d = $sql->fetch_assoc())
                                $data[] = $d;
                        }
                        else if(isset($_GET['fg']) ) {
                            $tpm = $conn->real_escape_string($_GET['fg']);
                            $sql = $conn->query("SELECT Player,FG FROM Players GROUP BY FG ORDER BY FG DESC");
                            while($d = $sql->fetch_assoc())
                                $data[] = $d;
                        }
                        else if(isset($_GET['ft']) ) {
                            $tpm = $conn->real_escape_string($_GET['ft']);
                            $sql = $conn->query("SELECT Player,FT FROM Players GROUP BY FT ORDER BY FT DESC");
                            while($d = $sql->fetch_assoc())
                                $data[] = $d;
                        }
                        else if(isset($_GET['reb']) ) {
                            $tpm = $conn->real_escape_string($_GET['reb']);
                            $sql = $conn->query("SELECT Player,REB FROM Players GROUP BY REB ORDER BY REB DESC");
                            while($d = $sql->fetch_assoc())
                                $data[] = $d;
                        }
                        else if(isset($_GET['stl']) ) {
                            $tpm = $conn->real_escape_string($_GET['stl']);
                            $sql = $conn->query("SELECT Player,STL FROM Players GROUP BY STL ORDER BY STL DESC");
                            while($d = $sql->fetch_assoc())
                                $data[] = $d;
                        }
                        else if(isset($_GET['tox']) ) {
                            $tpm = $conn->real_escape_string($_GET['tox']);
                            $sql = $conn->query("SELECT Player,TOX FROM Players GROUP BY TOX ORDER BY TOX ASC");
                            while($d = $sql->fetch_assoc())
                                $data[] = $d;
                        }
                        else {
                        $data = array();
                        $sql =$conn->query("SELECT * FROM Players");
                        while($d = $sql->fetch_assoc())
                            $data[] = $d;
                        }
            
                        exit(json_encode(['Players' => $data] )       );
                    } 
                    else if($_SERVER['REQUEST_METHOD'] == 'POST') {
                        echo 'POST';
                    }
                    else if($_SERVER['REQUEST_METHOD'] == 'PUT') {
                        echo 'PUT';
                    }
                    else if($_SERVER['REQUEST_METHOD'] == 'DELETE') {
                        echo 'DELETE';
                    }

