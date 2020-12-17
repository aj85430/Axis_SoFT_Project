import React, { Component } from "react";

import UserService from "../services/userservice";
import {Link } from "react-router-dom";

export default class IssueBook extends Component {
  constructor(props) {
    super(props);
    this.onChangeUserId= this.onChangeUserId.bind(this);
    this.onChangeBookId= this.onChangeBookId.bind(this);    
    this.onChangePeriod= this.onChangePeriod.bind(this);
    this.onChangeIssueDate= this.onChangeIssueDate.bind(this);
   

    this.saveBook = this.saveBook.bind(this);
    this.newBook = this.newBook.bind(this);

    this.state = {
      bookId: this.props.match.params.id,
      userId: null,
      period: null, 
      issueDate: "",
      

      submitted:false

    };

    
  }


  onChangeBookId(e) {
    this.setState({
      bookId: e.target.value
    });
  }

  onChangeUserId(e) {
    this.setState({
      userId: e.target.value
    });
  }

  onChangePeriod(e) {
    this.setState({
      period: e.target.value
    });
  }

  onChangeIssueDate(e){
      this.setState({
          issueDate: e.target.value
      })
  }

 

  saveBook() {
    var data = {
      bookId: this.state.bookId,
      userId: this.state.userId,
      period: this.state.period,
      issueDate: this.state.issueDate

    };

    UserService.issueBook(this.state.bookId, data)
      .then(response => {
        this.setState({
          bookId: response.data.bookId,
          userId: response.data.userId,
          period:response.data.period,
          issueDate:response.data.issueDate,
  
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
        userId: null,
        period: null, 
        issueDate: "",
        
  
        submitted:false
    });
  }

  render() {
    return (
      <div className="add">
        <div className="submit-form">
          {this.state.submitted ? (
            <div>
              <h4 className="ud">Yay! Book Issued Successfully!</h4>
              <button className="btn btn-success">
                <Link to="/books" className="ud">Back</Link>
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
                  onChange={this.onChangeBookId}
                  readOnly
                  name="bookId"
                />
              </div>

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
                <label htmlFor="issueDate" className="ud">Issue Date</label>
                <input
                  type="text"
                  className="form-control"
                  id="issueDate"
                  required
                  value={this.state.issueDate}
                  placeholder="YYYY-MM-DD"
                  onChange={this.onChangeIssueDate}
                  name="issueDate"
                />
              </div>

              <div className="form-group">
                <label htmlFor="period" className="ud">Period</label>
                <input
                  type="text"
                  className="form-control"
                  id="period"
                  required
                  value={this.state.period}
                  placeholder="Enter period in days"
                  onChange={this.onChangePeriod}
                  name="period"
                />
              </div>

              
              <button onClick={this.saveBook} disabled={
         this.state.userId === ""  
        || this.state.issueDate === "" || this.state.period === null  ? true : false} className="btn btn-success">
                Issue
              </button>&nbsp;&nbsp;
              <Link to="/books" className="ud"><button className="btn btn-success">Cancel</button></Link>
            </div>
          )}
        </div>
        </div>
      );
  }
}