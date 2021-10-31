addEventListener('DOMContentLoaded', () =>{
    const btn_menu = document.querySelector('.btn-menu')
    if(btn_menu){
        btn_menu.addEventListener('click', () =>{
            const items_menu = document.querySelector('.items-menu')
            items_menu.classList.toggle('mostrar')
        })
    }
})
