const $fragment = document.querySelector('#fragment');
const $list = document.querySelector('#list');

let inputData = {
    category : '',
    title : '',
    content : ''
}

const {category, title, content} = inputData;

const onChange = (e) => {
    if(e.target.name == "category") {
        inputData.category = e.target.value;
    } else if(e.target.name == "title") {
         inputData.title = e.target.value;
    } else if(e.target.name == "content") {
         inputData.content = e.target.value;
    }
}

const onSubmit = (e) => {
    if(!e.target.matches('#suggestion-form')) return;
    e.preventDefault();
    console.log(inputData);
    postSuggestion();
    render();
}

const getSuggestions = async () => {
    try {
        const res = await fetch('http://localhost:8080/api/suggestion');
        const result = await res.json();

        console.log(result);


        $list.innerHTML = await result.map(sug => {
                             return `<li>${sug.category} / ${sug.title} / ${sug.content} / ${sug.creator.name} / ${sug.regdate}</li>`;
                         }).join(' ');

    } catch(err) {
        console.log(err.message);
    }
}

const postSuggestion = async () => {
    try {
        const res = await fetch('http://localhost:8080/api/suggestion', {
        method : 'POST',
        headers : {'Content-Type' : 'application/json'},
        body : JSON.stringify(inputData)});

        const result = await res.json();

        inputData = {
            category : '',
            title : '',
            content : ''
        };

        console.log(result.message);
    } catch(err) {
        console.log(err.message);
    }
}

const render = () => {

    console.log(inputData);

    $fragment.innerHTML = `
     <h>건의사항</h>
    <form class="form" id="suggestion-form">
        <div class="form-group">
            <select name="category" value=${category} >
                <option value="0">*카테고리</option>
                <option value="1">카테고리1</option>
                <option value="2">카테고리2</option>
                <option value="3">카테고리3</option>
            </select>
        </div>
        <div class="form-froup">
            <input
                type="text"
                placeholder="제목"
                name="title"
                value=${title}
            >
        </div>
        <div class="form-froup">
            <input
                type="text"
                placeholder="내용"
                name="content"
                value=${content}
            >
        </div>
        <input type="submit" />
    </form>
    `;


    getSuggestions();
}

render();

document.addEventListener('submit', (e) => {
    onSubmit(e);
})

document.addEventListener('change', (e) => {
    onChange(e);
})

