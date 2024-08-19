import React, { useEffect, useState } from "react";
import axios from "axios";
import '../App.css';

const Board = () => {

  const postsAPI = "http://localhost:9007/posts"
  const uploadAPI = "http://localhost:9007/gallery/upload";

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [files, setFiles] = useState([]);
  const [posts, setPosts] = useState([]);

  const Java에업로드 = () => {
    //Form 특정값을 가져와서 넘겨줄 때 사용하는 객체
    //files에서 파일이 하나가 아니라 여러개 담을 배열 설정
    const formData = new FormData();
    Array.from(files).forEach((file) => {
      formData.append("files", file);
    });
    formData.append("title", title);
    formData.append("content", content);

    //java controller에 데이터 전송 post
    axios.post(uploadAPI, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    alert("자바로 이미지 전송했습니다.");
    //이미지 업로드를 db에 하고나서 업로드 된 이미지를 불러오기
    게시물가져오기();
  };

  //const ~ : 기능을 작성해놓고 필요할 때 기능을 사용하기 위해 설정
  const 게시물가져오기 = () => {
    axios
      .get(postsAPI) //java controller url api주소에서 데이터가져오기
      .then((response) => {
        setPosts(response.data);
        console.log(response.data);
      });
  };

  //맨처음 사이트 들어왔을 때 게시물을 바로 가져오게
  useEffect(() => {
    게시물가져오기();
  }, []);

  return (
    <div className="App">
      <div className="form-container">
        <table>
          <tbody>
            <tr>
              <td>
                <label>제목:</label>
              </td>
              <td>
                <input
                  type="text"
                  value={title}
                  onChange={(e) => setTitle(e.target.value)}
                />
              </td>
            </tr>
            <tr>
              <td>
                <label>내용:</label>
              </td>
              <td>
                <textarea
                  value={content}
                  onChange={(e) => setContent(e.target.value)}
                />
              </td>
            </tr>
            <tr>
              <td>
                <label htmlFor="a" className="file-label">이미지선택:
                <input
                  multiple
                  type="file"
                  className="img-input"
                  id="a"
                  onChange={(e) => setFiles(e.target.files)}
                />
                </label>
              </td>
            
              <td>
                <button onClick={Java에업로드}>이미지 업로드 버튼</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div className="posts-container">
        {posts.map((post) => (
          <div key={post.id} className="post">
            <h2> {post.title}</h2>
            <p>{post.content}</p>

            {/*post.files 존재할 경우만 && 뒤에 코드 실행
                        {post.files && post.files.map(file => (
                            <img key={file} src={file}/>
                        ))
                        }
                        , 로 구분해서 여러 파일 나눠서 배열로 저장
                        */}

            <div className="images">
            {post.imageUrl.split(",").map((image) => (
              <img key={image} src={`http://localhost:9007/images/${image}`} />
            ))}
            </div>

            <p>{post.createdAt}</p>
            <button>이미지 수정하기</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Board;
