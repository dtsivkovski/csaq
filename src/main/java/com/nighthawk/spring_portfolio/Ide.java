// // Enable syntax highlighting
// editor.session.setMode("ace/mode/javascript");

// // Enable autocompletion
// editor.setOptions({
//   enableBasicAutocompletion: true,
//   enableSnippets: true,
//   enableLiveAutocompletion: true,
// });

// // Enable error highlighting
// editor.getSession().on("changeAnnotation", function () {
//   var annotations = editor.getSession().getAnnotations();
//   // Handle errors here
// });

// //part 2

// // Send code to the server
// function sendCode() {
//     var code = editor.getValue();
//     // Use AJAX or WebSocket to send the code to the server
//   }
  
//   // Receive response from the server
//   function receiveResponse(response) {
//     // Handle response here
//   }


//   //code mirror

//   var editor = CodeMirror(document.getElementById("editor"), {
//     mode: "javascript",
//     lineNumbers: true,
//     autoCloseBrackets: true,
//     matchBrackets: true,
//     lint: true,
//   });

//   // Enable error highlighting
// editor.on("change", function () {
//     CodeMirror.signal(editor, "lint");
//   });
  
//   // Add custom linting rules
//   CodeMirror.registerHelper("lint", "javascript", function (text) {
//     var errors = [];
//     // Implement your linting rules here
//     return errors;
//   });

//   // Send code to the server
// function sendCode() {
//     var code = editor.getValue();
//     // Use AJAX or WebSocket to send the code to the server
//   }
  
//   // Receive response from the server
//   function receiveResponse(response) {
//     // Handle response here
//   }