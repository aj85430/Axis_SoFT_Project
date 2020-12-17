import React, { Component } from "react";
import BookService from "../services/bookservice";
import { Link } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import CardDeck from 'react-bootstrap/CardDeck';

export default class BookList extends Component {
  constructor(props) {
    super(props);
    this.retrieveBooks = this.retrieveBooks.bind(this);
    this.onChangeSearchBookName = this.onChangeSearchBookName.bind(this);
    this.searchBookName = this.searchBookName.bind(this);
    this.onChangeSearchAuthor = this.onChangeSearchAuthor.bind(this);
    this.searchAuthor= this.searchAuthor.bind(this);
    

    this.state = {
      books: [],
      searchBookName:"",
      searchAuthor:""
    };
  }

  componentDidMount() {
    this.retrieveBooks();
  }

  onChangeSearchBookName(e) {
    const searchBookName = e.target.value;

    this.setState({
      searchBookName: searchBookName
    });
  }

  onChangeSearchAuthor(e) {
    const searchAuthor = e.target.value;

    this.setState({
      searchAuthor: searchAuthor
    });
  }


  searchBookName() {
    BookService.findByBookName(this.state.searchBookName)
      .then(response => {
        this.setState({
          books: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  searchAuthor() {
    BookService.findByAuthor(this.state.searchAuthor)
      .then(response => {
        this.setState({
          books: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }


  retrieveBooks() {
    BookService.getAll()
      .then(response => {
        this.setState({
          books: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }
 

  render() {
    const {searchBookName, searchAuthor, books } = this.state;

    return (
      <div className="booklist">
      <div className="list row">
        <div className="col-md-6">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search By Book Name"
              value={searchBookName}
              onChange={this.onChangeSearchBookName}
            />
            <div className="input-group-append">
              <button
                className="btn btn-success"
                type="button"
                onClick={this.searchBookName}
              >
                Search
              </button>
            </div>
          </div>
        </div>

        <div className="col-md-6">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search By Author"
              value={searchAuthor}
              onChange={this.onChangeSearchAuthor}
            />
            <div className="input-group-append">
              <button
                className="btn btn-success"
                type="button"
                onClick={this.searchAuthor}
              >
                Search
              </button>
            </div>
          </div>
        </div>

        {this.state.books.map(
                    book=>
              <div>     
            <CardDeck>
              <Card>
                <Card.Body>
                  <Card.Title>Book {book.bookId}</Card.Title>
                  <Card.Text>
                    <strong>Name:</strong>  {book.bookName}<br></br>
                    <strong>Author:</strong>  {book.author}<br></br>
                    <strong>Price:</strong>  {book.price}<br></br>
                    <strong>Status:</strong>  {book.status}
                  </Card.Text>
                </Card.Body>
                <Card.Footer>
                <Link
                to={"/books/" + book.bookId}>
                <button type="button" className="btn btn-dark">Edit</button>
              </Link>&nbsp;&nbsp;
              
              {book.status!="Not Available"?

                <Link
                to={"/issueBook/" + book.bookId}>
                
                <button type="button" className="btn btn-dark">Issue</button>
              </Link>:""}

              
              
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