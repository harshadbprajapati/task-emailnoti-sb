import React from "react";
import {useEffect,useState } from "react";
import './form.css';
import baseurl from "../api/bootapi";
import axios from "axios";




const Displayform =()=>{

    useEffect(()=>{
        
        console.log("hello")
    }, []);

    const [student,setStudent] = useState({});

    const handleForm=(e)=>{
        console.log(student)
        studentRegistered(student);
        e.preventDefault();
    }

    const studentRegistered=(data)=>{
        axios.post(`${baseurl}/`,data).then(
            (response)=>{
                console.log(response)
            },
            (error)=>{
                console.log(error);
            }
        )
    }

        return(
            <div>
                <div class="box-1">
                    <div class="title_box">
                        <h2>Form</h2>
                        <p>Please fill all the texts!</p>
                    </div>
                    <div class="content_box">
                        <form onSubmit={handleForm}>
                        <p>
                            Name: <br />
                            <input type="text" name="Name" id="Name" placeholder="Type your full name here" required onChange={(e)=>{
                                setStudent({...student,student_name:e.target.value})
                            }}/>
                        </p>
                        <p>
                            Email: <br />
                            <input type="email" placeholder="Type your email here" required onChange={(e)=>{
                                setStudent({...student,student_email:e.target.value})
                            }}/>
                        </p>
                        <p>
                            Contact No.: <br />
                            <input type="tel" name="mobile" id="mobile" placeholder="Contact No." required onChange={(e)=>{
                                setStudent({...student,student_contact_number:e.target.value})
                            }}/>
                        </p>
                        <p>
                            ID: <br />
                            <input type="text" name="text" id="Address" placeholder="Type your ID here" required onChange={(e)=>{
                                setStudent({...student,student_email:e.target.value})
                            }}/>
                        </p>
                            <input type="submit" value="Done" />
                        </form>
                    </div>
                </div>
            </div>
        )
}
export default Displayform