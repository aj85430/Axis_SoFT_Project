import React, { Component } from 'react';
import Carousel from 'react-bootstrap/Carousel'
import { Switch, Route, Link } from "react-router-dom";
import image1 from '../image1.jpg';
import image2 from '../image2.jpg';
import photo1 from '../photo1.jpg';
import AddBook from "./add-book";
import Book from "./book";
import BookList from "./book-list";
import User from "./user";
import UserList from "./user-list";
import AddUser from "./add-user";
import IssueBook from "./issue-book";
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'
import About from "./about";
import photo2 from '../photo2.jpg';
import IssueBookList from "./issueBook-list";
import ReturnBook from "./return-book";
import fire from '../config/fire';
import Login from "./login";
import Home1 from './home1';




class Home extends Component {

  logout(){
    fire.auth().signOut();
  }
  render() {
    return (
      <div className="bg" style={{ backgroundImage:`url(${photo2})`, height:`150vh`}}>
          
          <Navbar bg="dark" expand="lg" variant="dark">
             <Navbar.Brand href="/home">BlueStone Library</Navbar.Brand>
                 <Navbar.Toggle aria-controls="basic-navbar-nav" />
                     <Navbar.Collapse id="basic-navbar-nav">
                       <Nav className="mr-auto">
                          <Nav.Link href="/home" active>Home</Nav.Link>
                          <Nav.Link href="/about" active>About</Nav.Link>
                          <NavDropdown title="Books" id="basic-nav-dropdown" active>
                              <NavDropdown.Item href="/books">See Books</NavDropdown.Item>
                              <NavDropdown.Item href="/issued">Issued Books</NavDropdown.Item>
                             {/* <NavDropdown.Item href="/returnBook">Return Book</NavDropdown.Item>*/}
                          </NavDropdown>
                          <Nav.Link href="/users" active>Users</Nav.Link>
                          <NavDropdown title="Add" id="basic-nav-dropdown" active>
                              <NavDropdown.Item href="/addBook">Add Book</NavDropdown.Item>
                              <NavDropdown.Item href="/addUser">Add User</NavDropdown.Item>
                          </NavDropdown>
                         
                          <Nav.Link href="/" active> <button align="right" className="logout" onClick={this.logout}>Logout</button></Nav.Link>
                          
                        </Nav>
                      </Navbar.Collapse>
          </Navbar>
          <Route exact path={["/", "/home"]} component={Home1} />  

          <div className="container mt-3">
            <Switch>
              <Route exact path="/books" component={BookList} />
              <Route exact path="/users" component={UserList} />
              <Route exact path="/addBook" component={AddBook}/>
              <Route exact path="/addUser" component={AddUser}/>
              <Route path="/books/:id" component={Book} />
              <Route path="/issueBook/:id" component={IssueBook} />
              <Route path="/users/:id" component={User} />
              
              <Route exact path="/about" component={About} />
              <Route exact path="/issued" component={IssueBookList} />
              <Route exact path="/returnBook/:id" component={ReturnBook} />
            </Switch>
         
        </div>
          
          
     </div>
  
    );
  }
}

export default Home