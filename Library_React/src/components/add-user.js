import React, { Component } from "react";
import UserService from "../services/userservice";
import {Link} from 'react-router-dom'

export default class AddUser extends Component {
  constructor(props) {
    super(props);
    this.onChangeUserName= this.onChangeUserName.bind(this);
    this.onChangeUserId= this.onChangeUserId.bind(this);    
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.saveUser = this.saveUser.bind(this);
    this.newUser= this.newUser.bind(this);

    this.state = {
      userId: null,
      userName: "",
      email: "",

      submitted:false

    };

    
  }

  onChangeUserId(e) {
    this.setState({
      userId: e.target.value
    });
  }

  onChangeUserName(e) {
    this.setState({
      userName: e.target.value
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }


  saveUser() {
    var data = {
      userId: this.state.userId,
      userName: this.state.userName,
      email: this.state.email

    };

    UserService.create(data)
      .then(response => {
        this.setState({
          userId: response.data.userId,
          userName: response.data.userName,
          email:response.data.email,
        
          submitted:true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newUser() {
    this.setState({
      userId: null,
      userName: "",
      email: "",

      submitted:false
    });
  }

  render() {
    return (
      <div className="add">
        <div className="submit-form">
          {this.state.submitted ? (
            <div>
              <h4 className="ud">Yay! User Added Successfully!</h4>
              <button className="btn btn-success" onClick={this.newUser}>
                Add
              </button>
            </div>
          ) : (
            <div>
              <div className="form-group">
                <label htmlFor="userId" className="ud">User Id</label>
                <input
                  type="text"
                  className="form-control"
                  id="userId"
                  required
                  value={this.state.userId}
                  placeholder="Enter User Id"
                  onChange={this.onChangeUserId}
                  name="userId"
                />
              </div>

              <div className="form-group">
                <label htmlFor="userName" className="ud">User Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="userName"
                  required
                  value={this.state.userName}
                  placeholder="Enter User Name"
                  onChange={this.onChangeUserName}
                  name="userName"
                />
              </div>
  
              <div className="form-group">
                <label htmlFor="email" className="ud">Email</label>
                <input
                  type="text"
                  className="form-control"
                  id="email"
                  required
                  value={this.state.email}
                  placeholder="Enter Email Id"
                  onChange={this.onChangeEmail}
                  name="email"
                />
              </div>

  
              <button onClick={this.saveUser} disabled={
        this.state.userId === "" || this.state.userName === "" || this.state.email === "" ? true : false
    } className="btn btn-success">
                Add
              </button>&nbsp;&nbsp;
              <Link to="/home" className="ud"><button className="btn btn-success">Cancel</button></Link>
            </div>
          )}
        </div>
        </div>
      );
  }
}