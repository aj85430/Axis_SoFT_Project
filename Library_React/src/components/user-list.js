import React, { Component } from "react";
import UserService from "../services/userservice";
import { Link } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import CardDeck from 'react-bootstrap/CardDeck';

export default class UserList extends Component {
  constructor(props) {
    super(props);
    this.retrieveUsers = this.retrieveUsers.bind(this);
    this.onChangeSearchUserName = this.onChangeSearchUserName.bind(this);
    this.searchUserName = this.searchUserName.bind(this);
    

   
    

    this.state = {
      users: [],
    };
  }

  componentDidMount() {
    this.retrieveUsers();
  }

  onChangeSearchUserName(e) {
    const searchUserName = e.target.value;

    this.setState({
      searchUserName: searchUserName
    });
  }

  searchUserName() {
    UserService.findByUserName(this.state.searchUserName)
      .then(response => {
        this.setState({
          users: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }


  retrieveUsers() {
    UserService.getAll()
      .then(response => {
        this.setState({
          users: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

 

  render() {
    const { searchUserName, users } = this.state;

    return (
      <div className="userlist">
      <div className="list row">   
      <div className="col-md-12">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search By User Name"
              value={searchUserName}
              onChange={this.onChangeSearchUserName}
            />
            <div className="input-group-append">
              <button
                className="btn btn-success"
                type="button"
                onClick={this.searchUserName}
              >
                Search
              </button>
            </div>
          </div>
        </div>
  
 
            
                  {this.state.users.map(
                    user=>
              <div>     
            <CardDeck>
              <Card>
                <Card.Body>
                  <Card.Title>User {user.userId}</Card.Title>
                  <Card.Text>
                    <strong>Name:</strong>  {user.userName}<br></br>
                    <strong>Email:</strong>  {user.email}<br></br>
                  </Card.Text>
                </Card.Body>
                <Card.Footer>
                <Link
                to={"/users/" + user.userId}
               >
                <button type="button" className="btn btn-dark">Edit</button>
              </Link>
                </Card.Footer>
              </Card>
              &nbsp;&nbsp;
            </CardDeck>
            <br></br>
            </div>
            
            
                    

                  )}
                  
                          
       
      </div>
      </div>
    );
  }
}