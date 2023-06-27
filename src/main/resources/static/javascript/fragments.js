function toggleHamburger() {
    const hamburger = document.getElementById("hamburgerLinks");
    const hamburgerIcon = document.getElementById("hamburgerIcon");
    if (hamburger.style.display === "none") {
        hamburger.style.display = "block";
        hamburgerIcon.innerHTML = "X Menu"
    } else {
        hamburger.style.display = "none";
        hamburgerIcon.innerHTML = "☰ Menu"
    }
}
