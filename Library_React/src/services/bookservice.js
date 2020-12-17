import http from "../http-common";

class BookService{

        getAll(){
          return http.get("/books");
        }

        getById(bookId) {
          return http.get(`/books/${bookId}`);
        }
      
        create(data) {
          return http.post("/books", data);
        }
      
        update(bookId, data) {
          return http.put(`/books/${bookId}`, data);
        }
      
        delete(bookId) {
          return http.delete(`/books/${bookId}`);
        }
      
        findByBookName(bookName){
          return http.get(`/books/title/${bookName}`);
        }

        findByAuthor(author){
          return http.get(`/books/author/${author}`);
        }

        getAvailable(){
          return http.get("/books/search/available");
        }

        getIssuedBooks(){
          return http.get("/books/issued");
        }
      
      }
    
    export default new BookService();
