import React, { Component } from "react";
import UserService from "../services/userservice";
import {Link } from "react-router-dom";

export default class User extends Component {
  constructor(props) {
    super(props);
    this.onChangeUserName= this.onChangeUserName.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.getUser = this.getUser.bind(this);
    this.updateUser = this.updateUser.bind(this);
    this.deleteUser = this.deleteUser.bind(this);

    this.state = {
      currentUser: {
        userId: null,
      userName: "",
      email: ""
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getUser(this.props.match.params.id);
  }

  onChangeUserName(e) {
    const name = e.target.value;

    this.setState(function(prevState) {
      return {
        currentUser: {
          ...prevState.currentUser,
          userName: name
        }
      };
    });
  }

  onChangeEmail(e) {
    const email = e.target.value;
    
    this.setState(prevState => ({
      currentUser: {
        ...prevState.currentUser,
        email:email
      }
    }));
  }



  getUser(userId) {
    UserService.getById(userId)
      .then(response => {
        this.setState({
          currentUser: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }


  updateUser() {
    UserService.update(
      this.state.currentUser.userId,
      this.state.currentUser
    )
      .then(response => {
        console.log(response.data);
        this.setState({
          message: "The User was updated successfully!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  deleteUser() {    
    UserService.delete(this.state.currentUser.userId)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/users')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() 
    {
        const { currentUser} = this.state;
    
        return (
          <div className="edit">
            {currentUser ? (
              <div className="edit-form">
                <h4 className="ud">User {currentUser.userId}</h4>
                <form>
                  <div className="form-group">
                    <label htmlFor="userName" className="ud">User Name</label>
                    <input
                      type="text"
                      className="form-control"
                      id="userName"
                      value={currentUser.userName}
                      onChange={this.onChangeUserName}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="email" className="ud">Email</label>
                    <input
                      type="text"
                      className="form-control"
                      id="email"
                      value={currentUser.email}
                      onChange={this.onChangeEmail}
                    />                 

                </div>                  
                </form> 
                
                <div className="buttons">
                <button type="button" className="btn btn-danger" onClick={this.deleteUser}>Delete</button>&nbsp;&nbsp;
            
                <button type="button" class="btn btn-warning" onClick={this.updateUser}>Update</button>&nbsp;&nbsp;
                <Link to="/users" className="ud"><button className="btn btn-success">Cancel</button></Link>
                
                <h3 className="ud">{this.state.message}</h3>
                </div>
              </div>
            ) : (
              <div>
                <br />
                <p className="ud">Please click on a User...</p>
              </div>
            )}
          </div>
        );
  }
  }