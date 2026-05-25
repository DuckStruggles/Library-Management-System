const BASE_URL = "http://localhost:8081/books";

async function addBook() {

    const book = {

        title: document.getElementById("title").value,

        author: document.getElementById("author").value,

        isbn: document.getElementById("isbn").value,

        category: document.getElementById("category").value,

        totalCopies:
            parseInt(document.getElementById("totalCopies").value),

        availableCopies:
            parseInt(document.getElementById("availableCopies").value),

        publishedYear:
            parseInt(document.getElementById("publishedYear").value)
    };

    try {

        const response = await fetch(BASE_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(book)
        });

        if(response.ok) {

            alert("Book Added Successfully!");

            clearForm();

            getBooks();

        } else {

            alert("Failed to add book");
        }

    } catch(error) {

        console.error(error);
    }
}

async function getBooks() {

    try {

        const response =
            await fetch(`${BASE_URL}?page=0&size=50`);

        const data = await response.json();

        renderBooks(data.content);

    } catch(error) {

        console.error(error);
    }
}

async function searchBooks() {

    const keyword =
        document.getElementById("searchInput").value;

    if(keyword.trim() === "") {

        getBooks();

        return;
    }

    try {

        const response =
            await fetch(
                `${BASE_URL}/search?keyword=${keyword}`
            );

        const data = await response.json();

        renderBooks(data);

    } catch(error) {

        console.error(error);
    }
}

function renderBooks(books) {

    const booksContainer =
        document.getElementById("booksContainer");

    booksContainer.innerHTML = "";

    if(books.length === 0) {

        booksContainer.innerHTML =
            "<p>No books found.</p>";

        return;
    }

    books.forEach(book => {

        booksContainer.innerHTML += `

            <div class="book-card">

                <h3>${book.title}</h3>

                <p>
                    <strong>Author:</strong>
                    ${book.author}
                </p>

                <p>
                    <strong>ISBN:</strong>
                    ${book.isbn}
                </p>

                <p>
                    <strong>Category:</strong>
                    ${book.category}
                </p>

                <p>
                    <strong>Total Copies:</strong>
                    ${book.totalCopies}
                </p>

                <p>
                    <strong>Available:</strong>
                    ${book.availableCopies}
                </p>

                <p>
                    <strong>Published:</strong>
                    ${book.publishedYear}
                </p>

                <button onclick="deleteBook(${book.id})">
                    Delete
                </button>

            </div>
        `;
    });
}

async function deleteBook(id) {

    try {

        const response =
            await fetch(`${BASE_URL}/${id}`, {

                method: "DELETE"
            });

        if(response.ok) {

            alert("Book Deleted");

            getBooks();

        } else {

            alert("Delete failed");
        }

    } catch(error) {

        console.error(error);
    }
}

function clearForm() {

    document.getElementById("title").value = "";

    document.getElementById("author").value = "";

    document.getElementById("isbn").value = "";

    document.getElementById("category").value = "";

    document.getElementById("totalCopies").value = "";

    document.getElementById("availableCopies").value = "";

    document.getElementById("publishedYear").value = "";
}