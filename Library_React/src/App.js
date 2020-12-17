import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Switch, Route, Link } from "react-router-dom";
import "./App.css";
import AddBook from "./components/add-book";
import Book from "./components/book";
import BookList from "./components/book-list";
import Home from "./components/home";
import User from "./components/user";
import UserList from "./components/user-list";
import AddUser from "./components/add-user";
import IssueBook from "./components/issue-book";
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'
import About from "./components/about";
import photo2 from './photo2.jpg';
import IssueBookList from "./components/issueBook-list";
import ReturnBook from "./components/return-book";
import fire from './config/fire';
import Login from "./components/login";



class App extends Component {
  constructor(props){
    super(props);
    this.state={
      user:{}
    }
  }

  componentDidMount(){
    this.authListener();
  }
  authListener(){
    fire.auth().onAuthStateChanged((user)=>{
      if(user){
        this.setState({user})
      }
      else{
        this.setState({user:null})
      }
    })
  }
    render() {
      return (
        
       <div className="App">
           {this.state.user? (<Home/>):(<Login/>)}

           
            
           </div>
          
  
         
      );
    }
  }
  
  export default App;