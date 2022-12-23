import React from "react";
import {useEffect,useState } from "react";
import './form.css';
import baseurl from "../api/bootapi";
import axios from "axios";
import {ToastContainer,toast} from 'react-toastify';



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
        // const functionThatReturnPromise = ()=>{axios.post(`${baseurl}/studentdetails`,data)};
        // toast.promise(
        // functionThatReturnPromise,
        // {
        //     pending: 'Promise is pending',
        //     success: 'Promise resolved 👌',
        //     error: 'Promise rejected 🤯'
        // }
// )
        axios.post(`${baseurl}/studentdetails`,data).then((response)=>{
                console.log("Success")
                toast.success("Registered Successfully!!");
            },(error)=>{
                console.log("Error");
                toast.error("Registration Failed!!");
            }
        );
    }

        return(
            <div>
                <div class="box-1">
                    <div class="title_box">
                        <h2>Form</h2>
                        <p>Please fill all the texts!</p>
                    </div>
                    <div class="content_box">
                        <ToastContainer />
                        <form onSubmit={handleForm}>
                        <p>
                            ID: <br />
                            <input type="text" name="text" id="Address" placeholder="Type your ID here" required onChange={(e)=>{
                                setStudent({...student,studentId:e.target.value})
                            }}/>
                        </p>
                        <p>
                            Contact No.: <br />
                            <input type="tel" name="mobile" id="mobile" placeholder="Contact No." required onChange={(e)=>{
                                setStudent({...student,studentContactNumber:e.target.value})
                            }}/>
                        </p>
                        <p>
                            Name: <br />
                            <input type="text" name="Name" id="Name" placeholder="Type your full name here" required onChange={(e)=>{
                                setStudent({...student,studentName:e.target.value})
                            }}/>
                        </p>
                        
                        
                        
                        <p>
                            Email: <br />
                            <input type="email" placeholder="Type your email here" required onChange={(e)=>{
                                setStudent({...student,studentEmail:e.target.value})
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