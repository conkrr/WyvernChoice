// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
let base_url;
//change to const

let choice_list_url = base_url + "admin"; //GET
let delete_date_url = base_url + "admin"; //POST with {deleteData}
let get_choice_url = base_url + "choice";