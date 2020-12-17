import React, { Component } from "react";
import BookService from "../services/bookservice";
import {Link } from "react-router-dom";

export default class AddBook extends Component {
  constructor(props) {
    super(props);
    this.onChangeBookName= this.onChangeBookName.bind(this);
    this.onChangeBookId= this.onChangeBookId.bind(this);    
    this.onChangeAuthor = this.onChangeAuthor.bind(this);
    this.onChangePrice = this.onChangePrice.bind(this);
    this.onChangeStatus = this.onChangeStatus.bind(this);
    this.saveBook = this.saveBook.bind(this);
    this.newBook = this.newBook.bind(this);

    this.state = {
      bookId: null,
      bookName: "",
      author: "", 
      price:null,
      status: 'Available',

      submitted:false

    };

    
  }

  onChangeBookId(e) {
    this.setState({
      bookId: e.target.value
    });
  }

  onChangeBookName(e) {
    this.setState({
      bookName: e.target.value
    });
  }

  onChangeAuthor(e) {
    this.setState({
      author: e.target.value
    });
  }

  onChangePrice(e){
      this.setState({
          price: e.target.value
      })
  }

  onChangeStatus(e) {
    this.setState({
      status: e.target.value
    });
  }

  saveBook() {
    var data = {
      bookId: this.state.bookId,
      bookName: this.state.bookName,
      author: this.state.author,
      price:this.state.price,
      status:this.state.status

    };

    BookService.create(data)
      .then(response => {
        this.setState({
          bookId: response.data.bookId,
          bookName: response.data.bookName,
          author:response.data.author,
          price:response.data.price,
          status: response.data.status,

          submitted:true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newBook() {
    this.setState({
      bookId: null,
      bookName: "",
      author: "",
      price:null,
      status: "",

      submitted:false
    });
  }

  render() {
    return (
      <div className="add">
        <div className="submit-form">
          {this.state.submitted ? (
            <div>
              <h4 className="ud">Yay! Book Added Successfully!</h4>
              <button className="btn btn-success" onClick={this.newBook}>
                Add
              </button>
            </div>
          ) : (
            <div>
              <div className="form-group">
                <label htmlFor="bookId" className="ud">Book Id</label>
                <input
                  type="text"
                  className="form-control"
                  id="bookId"
                  required
                  value={this.state.bookId}
                  placeholder="Enter Book Id"
                  onChange={this.onChangeBookId}
                  name="bookId"
                />
              </div>

              <div className="form-group">
                <label htmlFor="bookName" className="ud">Book Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="bookName"
                  required
                  value={this.state.bookName}
                  placeholder="Enter Book Name"
                  onChange={this.onChangeBookName}
                  name="bookName"
                />
              </div>
  
              <div className="form-group">
                <label htmlFor="author" className="ud">Author</label>
                <input
                  type="text"
                  className="form-control"
                  id="author"
                  required
                  value={this.state.author}
                  placeholder="Enter Author"
                  onChange={this.onChangeAuthor}
                  name="author"
                />
              </div>

              <div className="form-group">
                <label htmlFor="price" className="ud">Price</label>
                <input
                  type="text"
                  className="form-control"
                  id="price"
                  required
                  value={this.state.price}
                  placeholder="Enter Price"
                  onChange={this.onChangePrice}
                  name="price"
                />
              </div>

              <div className="form-group">
                <label htmlFor="status" className="ud">Status</label>
                <select className="form-control" value={this.state.status} onChange={this.onChangeStatus}>
                  <option value="Available">Available</option>
                  <option value="Not Available">Not Available</option>
                </select>
              </div>

  
              <button onClick={this.saveBook} disabled={
        this.state.bookId === "" || this.state.bookName === "" || this.state.author === "" 
        || this.state.price === ""    ? true : false} className="btn btn-success">
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