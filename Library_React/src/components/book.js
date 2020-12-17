import React, { Component } from "react";
import BookService from "../services/bookservice";
import {Link } from "react-router-dom";

export default class Book extends Component {
  constructor(props) {
    super(props);
    this.onChangeBookName= this.onChangeBookName.bind(this);
    this.onChangeAuthor = this.onChangeAuthor.bind(this);
    this.onChangePrice = this.onChangePrice.bind(this);
    this.onChangeStatus = this.onChangeStatus.bind(this);
    this.getBook = this.getBook.bind(this);
    this.updateBook = this.updateBook.bind(this);
    this.deleteBook = this.deleteBook.bind(this);

    this.state = {
      currentBook: {
        bookId: null,
      bookName: "",
      author: "", 
      price:null,
      status: ""
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getBook(this.props.match.params.id);
  }

  onChangeBookName(e) {
    const title = e.target.value;

    this.setState(function(prevState) {
      return {
        currentBook: {
          ...prevState.currentBook,
          bookName: title
        }
      };
    });
  }

  onChangeAuthor(e) {
    const author = e.target.value;
    
    this.setState(prevState => ({
      currentBook: {
        ...prevState.currentBook,
        author:author
      }
    }));
  }

  onChangePrice(e) {
    const price = e.target.value;
    
    this.setState(prevState => ({
      currentBook: {
        ...prevState.currentBook,
        price:price
      }
    }));
  }


  onChangeStatus(e) {
    const status = e.target.value;
    
    this.setState(prevState => ({
      currentBook: {
        ...prevState.currentBook,
        status:status
      }
    }));
  }


  getBook(bookId) {
    BookService.getById(bookId)
      .then(response => {
        this.setState({
          currentBook: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }


  updateBook() {
    BookService.update(
      this.state.currentBook.bookId,
      this.state.currentBook
    )
      .then(response => {
        console.log(response.data);
        this.setState({
          message: "The Book was updated successfully!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  deleteBook() {    
    BookService.delete(this.state.currentBook.bookId)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/books')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() 
    {
        const { currentBook} = this.state;
    
        return (
          <div className="edit">
            {currentBook ? (
              <div className="edit-form">
                <h4 className="ud">Book {currentBook.bookId}</h4>
                <form>
                  <div className="form-group">
                    <label htmlFor="bookName" className="ud">Book Name</label>
                    <input
                      type="text"
                      className="form-control"
                      id="bookName"
                      value={currentBook.bookName}
                      onChange={this.onChangeBookName}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="author" className="ud">Author</label>
                    <input
                      type="text"
                      className="form-control"
                      id="author"
                      value={currentBook.author}
                      onChange={this.onChangeAuthor}
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="price" className="ud">Price</label>
                    <input
                      type="text"
                      className="form-control"
                      id="price"
                      value={currentBook.price}
                      onChange={this.onChangePrice}
                 />
                 </div>

                 <div className="form-group">
                    <label htmlFor="status">Status</label>
                    <input
                      type="text"
                      className="form-control"
                      id="status"
                      value={currentBook.status}
                      onChange={this.onChangeStatus}
                 />
                 </div>
    
                  
                </form>
    
                <div className="buttons">
                <button type="button" className="btn btn-danger" onClick={this.deleteBook}>Delete</button>&nbsp;&nbsp;              
  
                <button type="submit" className="btn btn-warning" onClick={this.updateBook}>Update</button>&nbsp;&nbsp;
                <Link to="/books" className="ud"><button className="btn btn-success">Cancel</button></Link>
                <br></br>
                <h3 className="ud">{this.state.message}</h3>
                </div>
              </div>
            ) : (
              <div>
                <br />
                <p className="ud">Please click on a Book...</p>
              </div>
            )}
          </div>
        );
  }
  }