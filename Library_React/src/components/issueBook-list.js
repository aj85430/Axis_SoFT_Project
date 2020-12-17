import React, { Component } from "react";
import BookService from "../services/bookservice"
import UserService from "../services/userservice"
import Table from 'react-bootstrap/Table'
import {Link } from "react-router-dom";

export default class IssueBookList extends Component {
  constructor(props) {
    super(props);
    this.retrieveIssuedBooks = this.retrieveIssuedBooks.bind(this);
    this.onChangeSearchIssuedBooks = this.onChangeSearchIssuedBooks.bind(this);
    this.searchIssuedBooks= this.searchIssuedBooks.bind(this);
    
    this.state = {
      issuedbooks: [],
      searchIssuedBooks:""     
    };
  }

  componentDidMount() {
    this.retrieveIssuedBooks();
  }


  retrieveIssuedBooks() {
    BookService.getIssuedBooks()
      .then(response => {
        this.setState({
          issuedbooks: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  onChangeSearchIssuedBooks(e) {
    const searchIssuedBooks = e.target.value;

    this.setState({
      searchIssuedBooks: searchIssuedBooks
    });
  }

  searchIssuedBooks() {
    UserService.findIssuedBooksByUserId(this.state.searchIssuedBooks)
      .then(response => {
        this.setState({
          issuedbooks: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

 

  render() {
    const {searchIssuedBooks} = this.state;

    return (
      <div className="issuebooklist">
        <div className="container">
           <div className="col-md-12">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search By User Id"
              value={searchIssuedBooks}
              onChange={this.onChangeSearchIssuedBooks}
            />
            <div className="input-group-append">
              <button
                className="btn btn-success"
                type="button"
                onClick={this.searchIssuedBooks}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <h3 className="ib">All Issued Books</h3>
        <div className="container">
            <Table striped bordered hover variant="light" className="table">
                <thead>
                    <tr>
                        <th>Issue Id</th>
                        <th>User Id</th>
                        <th>Book Id</th>
                        <th>Issue Date</th>
                        <th>Return Date</th>
                        <th>Period</th>
                        <th>Fine</th> 
                        <th>Return</th>                      
                    </tr>
                </thead>
                <tbody>
                        {
                            this.state.issuedbooks.map(
                                books =>
                                    <tr key={books.issueId}>
                                        <td>{books.issueId}</td>
                                        <td>{books.userId}</td>
                                        <td>{books.bookId}</td>
                                        <td>{books.issueDate}</td>
                                        <td>{books.returnDate}</td>
                                        <td>{books.period}</td>
                                        <td>{books.fine}</td>  
                                        <td>
                                          <Link to={"/returnBook/" + books.issueId}>
                                          {books.returnDate==null?<button type="button" className="btn btn-dark" >Return</button>:""}
              </Link></td>                                        
                                    </tr>
                                )
                        }
                    </tbody>
                    </Table>
                </div>
            </div>
            </div>
        )
    }
}