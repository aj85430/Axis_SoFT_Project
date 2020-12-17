import React, {Component} from 'react';
import fire from '../config/fire';


class Login extends Component{

    constructor(props){
        super(props)
        this.login=this.login.bind(this);
        this.handleChange=this.handleChange.bind(this);
        this.signup=this.signup.bind(this);
        this.state={
            email:"",
            password: ""
        }
    }
    login(e){
        e.preventDefault();
        fire.auth().signInWithEmailAndPassword(this.state.email,this.state.password).then((u)=>{
            console.log(u)
        }).catch((err)=>{
            console.log(err)
        })
    }
    signup(e){
        e.preventDefault();
        fire.auth().createUserWithEmailAndPassword(this.state.email,this.state.password).then((u)=>{
            console.log(u)
        }).catch((err)=>{
            console.log(err)
        })
    }
    handleChange(e){
        this.setState({
            [e.target.name]:e.target.value
        })
    }

    render(){
        return(
            <div className="login">
            <div className="loginContainer">
            
            <form>
                <div className="form-group">
                    <label for="email">Email</label>
                    <input type="email" className="form-control" id="email" 
                    onChange={this.handleChange} value={this.state.email} placeholder="Enter email address" name="email" />
                </div>

                <div className="form-group">
                <label for="password">Password</label>
                <input type="password" className="form-control" id="password" 
                onChange={this.handleChange} value={this.state.password} placeholder="Enter password" name="password" />
                </div>

                <button type="submit" onClick={this.login} className="btn btn-dark">Login</button>&nbsp;&nbsp;
                <button type="submit" onclick={this.signup} className="btn btn-dark">SignUp</button>
            </form>
            </div>
            </div>
        )
    }
}

export default Login;