import http from "../http-common";

class UserService{

        getAll(){
            return http.get("/users");
        }

        getById(userId) {
            return http.get(`/users/${userId}`);
        }
        
        create(data) {
            return http.post("/users", data);
        }
        
        update(userId, data) {
            return http.put(`/users/${userId}`, data);
        }
        
        delete(userId) {
            return http.delete(`/users/${userId}`);
        }

        issueBook(bookId, data){
            return http.post(`/books/issue/${bookId}`, data);
        }

        returnBook(issueId, data){
            return http.put(`/books/return/${issueId}`, data);
        }

        findByUserName(userName){
            return http.get(`/users/name/${userName}`);
        }

        findIssuedBooksByUserId(userId){
            return http.get(`/books/issued/${userId}`)
        }

        
    
    }
    
    export default new UserService();
