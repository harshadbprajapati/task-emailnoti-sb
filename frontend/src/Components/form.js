import React from "react";
import { Component } from "react";
import './form.css';

class Displayform extends Component{
    render(){
        return(
            <div>
                <body>
                <div class="box-1">
                    <div class="title_box">
                        <h2>Form</h2>
                        <p>Please fill all the texts!</p>
                    </div>
                    <div class="content_box">
                        <form action="action.php" method="get">
                        <p>
                            Name: <br />
                            <input type="text" name="Name" id="Name" placeholder="Type your full name here" required />
                        </p>
                        <p>
                            Email: <br />
                            <input type="email" placeholder="Type your email here" required />
                        </p>
                        <p>
                            Contact No.: <br />
                            <input type="tel" name="mobile" id="mobile" placeholder="Contact No." required />
                        </p>
                        <p>
                            ID: <br />
                            <input type="text" name="text" id="Address" placeholder="Type your ID here" required></input>
                        </p>
                            <input type="submit" value="Done" />
                        </form>
                    </div>
                </div>
                </body>
            </div>
        )
    }
}
export default Displayform