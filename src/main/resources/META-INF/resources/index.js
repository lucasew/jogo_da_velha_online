// const header = document.getElementsByTagName("header").item(0).addEventListener('click', () => {
//     if (document.body.classList.contains("dark")) {
//         document.body.classList.remove("dark")
//     } else {
//         document.body.classList.add("dark")
//     }
// })
//

function goto(href) {
    let u = new URL(window.location)
    u.pathname = href
    window.location.href = u.toString()
}

const header = document.getElementsByTagName("header").item(0)
header.addEventListener('click', (e) => {
    goto("/")
})
header.style.cursor = "pointer"

function getAuthenticationElement(element) {
    const elem = document.getElementById(`input-${element}`);
    const ls = localStorage.getItem(element);
    if (elem == null) {
        if (ls == null) {
            return "";
        } else {
            console.log(`encontrado ${element} no localstorage`)
            return ls;
        }
    } else {
        console.log(`encontrado ${element} em formulário`)
        return elem.value;
    }
}

function getAuthenticationData() {
    const user = getAuthenticationElement("user");
    const password = getAuthenticationElement("password")
    return {
        user,
        password
    }
}

function wrappedFetch(url, options) {
    if (options === undefined) {
        options = {}
    }
    const {user, password} = getAuthenticationData();
    if (options.headers == undefined) {
        options.headers = new Headers();
    }
    options.headers.append('Authorization', "Basic " + btoa(`${user}:${password}`))
    return fetch(url, options)
}

function storeFormLoginData() {
    const {user, password} = getAuthenticationData();
    localStorage.setItem("user", user);
    localStorage.setItem("password", password);
}

async function check_login() {
    const res = await wrappedFetch("/api/login-status");
    return res.ok
}
async function handle_login() {
    if (!await check_login()) {
        alert("Login inválido");
    } else {
        await storeFormLoginData();
        alert("Logado com sucesso")
        goto("/main.html");
    }
}

async function handle_signup() {
    const {user, password} = getAuthenticationData();
    const res = await fetch(`/api/cadastro?usuario=${user}&senha=${password}`)
    if (res.status) {
        alert("Usuário já existente");
    }
    console.log(res)
}

