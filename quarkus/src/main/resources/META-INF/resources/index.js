// const header = document.getElementsByTagName("header").item(0).addEventListener('click', () => {
//     if (document.body.classList.contains("dark")) {
//         document.body.classList.remove("dark")
//     } else {
//         document.body.classList.add("dark")
//     }
// })
//

function goto(href) {
    window.location.href = href
}

const header = document.getElementsByTagName("header").item(0)
header.addEventListener('click', (e) => {
    goto("/")
})
header.style.cursor = "pointer"

function handle_login() {
    alert("login")
}

function handle_signup() {
    alert("cadastro")
}

