import React, { Component } from "react";

import UserService from "../services/userservice";
import { Link } from "react-router-dom";

export default class ReturnBook extends Component {
  constructor(props) {
    super(props);
    this.onChangeIssueId= this.onChangeIssueId.bind(this);
    this.onChangeReturnDate= this.onChangeReturnDate.bind(this);

    this.saveBook = this.saveBook.bind(this);
    this.newBook = this.newBook.bind(this);

    this.state = {
      
      issueId: this.props.match.params.id,
      issueDate: "",
      

      submitted:false

    };

    
  }


  onChangeIssueId(e) {
    this.setState({
      issueId: e.target.value
    });
  }

  onChangeReturnDate(e){
      this.setState({
          returnDate: e.target.value
      })
  }

 

  saveBook() {
    var data = {      
      returnDate: this.state.returnDate
    };

    UserService.returnBook(this.state.issueId, data)
      .then(response => {
        this.setState({

          returnDate:response.data.returnDate,
  
          submitted:true
        });
        const res=response.data
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newBook() {
    this.setState({
        issueId: null,
        returnDate: "",       
  
        submitted:false
    });
  }

  render() {
      const {res}=this.state
    return (
      <div className="returnbook">
      <div className="add">
        <div className="submit-form">
          {this.state.submitted ? (
            <div>
              <h4 className="ud">Yay! Book Returned Successfully!!<br></br>{res}</h4>
              <button className="btn btn-success" >
              <Link to="/issued" className="ud">Back</Link>
              </button>
            </div>
          ) : (
            <div>
              <div className="form-group">
                <label htmlFor="bookId" className="ud">Issue Id</label>
                <input
                  type="text"
                  className="form-control"
                  id="issueId"
                  required
                  value={this.state.issueId}
                  onChange={this.onChangeIssueId}
                  readOnly
                  name="issueId"
                />
              </div>

              <div className="form-group">
                <label htmlFor="issueDate" className="ud">Return Date</label>
                <input
                  type="text"
                  className="form-control"
                  id="returnDate"
                  required
                  value={this.state.returnDate}
                  placeholder="YYYY-MM-DD"
                  onChange={this.onChangeReturnDate}
                  name="returnDate"
                />
              </div>

  
              <button onClick={this.saveBook} disabled={
        this.state.issueId === "" || this.state.returnDate === ""   ? true : false} className="btn btn-success">
                Return
              </button>&nbsp;&nbsp;
              <Link to="/issued" className="ud"><button className="btn btn-success">Cancel</button></Link>
            </div> 
          )}
        </div>
        </div>
        </div>
      );
  }
}