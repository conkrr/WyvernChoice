// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point

//Base url: This will be the url we generate in AWS
let base_url;

//All unique urls generated for each request
let choice_list_url = base_url + "admin";                    //GET
let delete_date_url = base_url + "admin";                    //POST with {deleteData}
let get_choice_url = base_url + "choice";                    //GET with {choiceID}
let create_choice_url = base_url + "choice";                 //GET
let finalize_choice_url = base_url + "finalChoice";          //POST
let register_user_url = base_url + "register";               //POST
let get_alternative_url = base_url + "alternative";          //GET with {alternativeID}
let add_approval_url = base_url + "addapproval";             //POST
let remove_approval_url = base_url + "removeapproval";       //POST
let add_disapproval_url = base_url + "adddisapproval";       //POST
let remove_disapproval_url = base_url + "removedisapproval"; //POST
let create_feedback_url = base_url + "feedback";             //POST