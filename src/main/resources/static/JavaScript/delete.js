let radioBoolean = true;

let toggle = () => {

    let element = document.getElementById("deleteButton");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
       element.removeAttribute("hidden");
    } else {
       element.setAttribute("hidden", "hidden");
    }
  }