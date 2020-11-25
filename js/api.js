// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point

//Base url: This will be the url we generate in AWS
var base_url = "https://uyttzmj0g3.execute-api.us-east-1.amazonaws.com/testStage/";
var admin_url;

//All unique urls generated for each request
var choice_list_url = admin_url + "admin";                    //GET
var delete_date_url = admin_url + "admin";                    //POST with {deleteData}
var get_choice_url = base_url + "choice";                    //GET with {choiceID}
var create_choice_url = base_url + "choice";                 //GET
var finalize_choice_url = base_url + "finalChoice";          //POST
var register_user_url = base_url + "register";               //POST
var get_alternative_url = base_url + "alternative";          //GET with {alternativeID}
var add_approval_url = base_url + "addapproval";             //POST
var remove_approval_url = base_url + "removeapproval";       //POST
var add_disapproval_url = base_url + "adddisapproval";       //POST
var remove_disapproval_url = base_url + "removedisapproval"; //POST
var create_feedback_url = base_url + "feedback";             //POST