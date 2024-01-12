


const toasts = {
    success_add: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Add successful !',
        alert: 'success'
    },
    success_signUp: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Sign up successful !',
        alert: 'success',
    },
    success_delete: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Delete successful !',
        alert: 'success',
    },
    success_update: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Update successful !',
        alert: 'success',
    },
    success_change: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Change successful !',
        alert: 'success'
    },
    success_order: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Order successful !',
        alert: 'success'
    },
    success: {
        icon: '<i class="fa fa-check-circle" aria-hidden="true"></i>',
        msg: 'Buy successful !',
        alert: 'success'
    },
    error_login: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'You need to login !',
        alert: 'error'
    },
    error_empty: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Your cart is empty !',
        alert: 'error'
    },
    error_signUp: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Sign up unsuccess !',
        alert: 'error'
    },
    error_delete: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Delete unsuccess !',
        alert: 'error'
    },
    error_change: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Change user information unsuccess !',
        alert: 'error'
    },
    error_add: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'Add new product unsuccess !',
        alert: 'error'
    },
    error_update: {
        icon: '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>',
        msg: 'update product unsuccess !',
        alert: 'error'
    }
}

function createToast(status) {
    let toast = document.createElement('div')
    toast.className = `toast ${status} ${toasts[status].alert}`

    toast.innerHTML = `
    ${toasts[status].icon}
    <span class="msg">${toasts[status].msg}</span>
    <span class="countdown"></span>
    `
    document.querySelector('#toasts').appendChild(toast)

    setTimeout(() => {
        toast.style.animation = 'hide_slide 1s ease forwards'
    }, 4000)
    setTimeout(() => {
        toast.remove()
    }, 6000)


}

let msg = document.getElementById("message");
createToast(msg.value);
msg.value = "";



