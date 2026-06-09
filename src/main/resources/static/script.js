const BASE_URL = "http://localhost:8081/books";

let currentPage = 0;
const pageSize = 5;
let searchTimeout;

function renderPagination(pageData) {

    let html = "";

    if (!pageData.first) {

        html += `
            <button onclick="getBooks(${pageData.number - 1})">
                Previous
            </button>
        `;
    }

    html += `
        <span>
            Page ${pageData.number + 1}
            of ${pageData.totalPages}
        </span>
    `;

    if (!pageData.last) {

        html += `
            <button onclick="getBooks(${pageData.number + 1})">
                Next
            </button>
        `;
    }

    document.getElementById("pagination").innerHTML = html;
}

function handleSearchInput() {

    clearTimeout(searchTimeout);

    searchTimeout = setTimeout(() => {

        fetchSuggestions();

    }, 400);
}

function selectSuggestion(bookId) {

    window.location.href =
        `book-details.html?id=${bookId}`;
}

async function searchBooks() {

    const keyword =
        document.getElementById("searchInput").value;

    if (keyword.trim() === "") {

        getBooks();
        return;
    }

    try {

        const response =
            await fetch(
                `${BASE_URL}/search?keyword=${encodeURIComponent(keyword)}`
            );

        const data =
            await response.json();

        renderBooks(data.content);

        document.getElementById("pagination").innerHTML = "";

    } catch (error) {

        console.error(error);
    }
}

async function fetchSuggestions() {

    const keyword =
        document.getElementById("searchInput").value;

    if (keyword.trim() === "") {

        document.getElementById("suggestions").innerHTML = "";
        document.getElementById("suggestions").style.display = "none";

        return;
    }

    try {

        const response =
            await fetch(
                `${BASE_URL}/suggestions?q=${encodeURIComponent(keyword)}`
            );

        const suggestions =
            await response.json();

        renderSuggestions(suggestions);

    } catch (error) {

        console.error(error);
    }
}

function renderSuggestions(suggestions) {

    const container =
        document.getElementById("suggestions");

    container.innerHTML = "";

    if (suggestions.length === 0) {

        container.style.display = "none";
        return;
    }

    container.style.display = "block";

    suggestions.forEach(book => {

        container.innerHTML += `
            <div
                class="suggestion-item"
                onclick="selectSuggestion(${book.id})">

                <strong>${book.title}</strong><br>

                <small>${book.author}</small>

            </div>
        `;
    });
}

async function getBooks(page = 0) {

    currentPage = page;

    try {

        const response =
            await fetch(
                `${BASE_URL}?page=${page}&size=${pageSize}`
            );

        const data =
            await response.json();

        renderBooks(data.content);

        renderPagination(data);

    } catch (error) {

        console.error(error);
    }
}

function renderBooks(books) {

    const booksContainer =
        document.getElementById("booksContainer");

    booksContainer.innerHTML = "";

    if (!books || books.length === 0) {

        booksContainer.innerHTML =
            "<p>No books found.</p>";

        return;
    }

    books.forEach(book => {

        const coverUrl =
            `https://covers.openlibrary.org/b/isbn/${book.isbn}-M.jpg`;

        booksContainer.innerHTML += `
            <div
                class="book-card"
                onclick="viewBook(${book.id})">

                <img
                    src="${coverUrl}"
                    alt="${book.title}"
                    class="book-cover"
                    onerror="this.src='images/no-img.jpg'">

                <div class="book-info">

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
                        <strong>Available:</strong>
                        ${book.availableCopies}
                        /
                        ${book.totalCopies}
                    </p>

                    <p>
                        <strong>Published:</strong>
                        ${book.publishedYear}
                    </p>

                    <button
                        onclick="event.stopPropagation(); deleteBook(${book.id})">

                        Delete

                    </button>

                </div>

            </div>
        `;
    });
}

function viewBook(id) {

    window.location.href =
        `book-details.html?id=${id}`;
}

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

        if (response.ok) {

            alert("Book Added Successfully!");

            clearForm();

            getBooks();

        } else {

            alert("Failed to add book");
        }

    } catch (error) {

        console.error(error);
    }
}

async function deleteBook(id) {

    try {

        const response =
            await fetch(`${BASE_URL}/${id}`, {

                method: "DELETE"
            });

        if (response.ok) {

            alert("Book Deleted");

            getBooks(currentPage);

        } else {

            alert("Delete Failed");
        }

    } catch (error) {

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

document.addEventListener("click", function(event) {

    const searchInput =
        document.getElementById("searchInput");

    const suggestions =
        document.getElementById("suggestions");

    if (
        searchInput &&
        suggestions &&
        !searchInput.contains(event.target) &&
        !suggestions.contains(event.target)
    ) {

        suggestions.style.display = "none";
    }
});

const searchInput =
    document.getElementById("searchInput");

if (searchInput) {

    searchInput.addEventListener("keydown", function(event) {

        if (event.key === "Enter") {

            searchBooks();
        }
    });
}