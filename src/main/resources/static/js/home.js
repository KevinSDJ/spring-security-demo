const ButtonsDelete = document.querySelectorAll('.button-delete')
const ButtonsEdit = document.querySelectorAll('.button-edit')

ButtonsDelete.forEach(e => {
    e.onclick = deleteUser
}
)
ButtonsEdit.forEach(e => {
    e.onclick = editUser
})

function deleteUser(e) {
    swal({
        title: "Are you sure?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                fetch("http://localhost:8080/admin/users/" + e.target.id, { method: 'DELETE' })
                    .then(res => res.json())
                    .then(res => {
                        console.log(res)
                        swal(res.message, {
                            icon: "success",
                        })
                        .then((close)=>{
                            window.location.reload()
                        })
                        
                    },(error) => {
                        console.log(error)
                        swal("Error", {
                            icon: "error"
                        })
                    })
            } else {
                swal("Your user is safe!");
            }
        });
}
function editUser(e) {
    swal({
        title: "Are you sure?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                console.log(e.target.id)
                swal("User Edit", {
                    icon: "success",
                });
            } else {
                swal("Good decition!");
            }
        });
}