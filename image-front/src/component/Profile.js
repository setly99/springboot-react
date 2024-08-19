import React, { useEffect, useState } from "react";
import '../css/Profile.css';
import axios from "axios";

const Profile = () => {

    const watchAPI = "http://localhost:9007/profile/watching";
    const uploadAPI = "http://localhost:9007/profile/upload";

    const [files, setFiles] = useState([]);
    const [username, setUsername] = useState('');
    const [profile, setProfile] = useState([]);
    const [userId, setUserId] = useState(null);

    const 파일변경기능 = (e) => {
        //파일을 변경했을 때 프로필 썸네일에 이미지들 주소가 넘어갈 수 있도록 설정
        const 선택한파일들 = Array.from(e.target.files);
        console.log("선택한파일들", 선택한파일들);

        setFiles(선택한파일들);
    };

    const 유저네임변경 = (e) => {
        setUsername(e.target.value);
    }

    //1. fetch버전 : 설치가 필요없는 리액트제공 java백엔드와 통신기능
    const 이미지업로드1 = () => {
        const formData = new FormData(); // files 이미지 파일이 여러개라서 묶어서보내려고
        Array.from(files).forEach((file) => {
            formData.append("files", file);
        });
        formData.append("username", username);

        fetch(uploadAPI, {
            method: "POST",
            headers: {'Content-Type' : 'multipart/form-data'},//데이터에 파일이포함됨을알림
            body: formData
        })
        //mysql DB에 값 넣기 성공했다면
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            //db저장된 프로필사진,닉네임보여주기
            게시물가져오기();
        })

    }

    //2. axios async await 버전 : 3 번의 업그레이드 버전 try/catch 오류처리
    //async() : 이 기능에는 잠시 대기해야 할 코드가 적혀있다.
    const 이미지업로드2 = async() => {
        const formData = new FormData();
        Array.from(files).forEach((file) => {
            formData.append("files", file);
        });
        formData.append("username", username);
        //await formData를 가져오기 전까지 잠시 대기
        await axios.post(uploadAPI, formData);
        게시물가져오기();

    };

    //3. axios then 버전
    const 이미지업로드3 = () => {
        const formData = new FormData();
        Array.from(files).forEach((file) => {
            formData.append("files", file);
        });
        formData.append("username", username);
        
        //삼항 연산자를 이용해서 수정기능을 위한 url로 변경 예정

        axios.post(uploadAPI, formData, {
            headers: {'Content-Type' : 'multipart/form-data'}
        })
        .then((response) => {
        /*fetch에는 이 기능 필요, axios는 불필요(알아서 포함)
            return response.json();
        })
        .then((data) => {
        */
            const data = response.data;
            게시물가져오기();
        })

    }

    //
    useEffect(() => {
        게시물가져오기();
    }, []);

    // -> axios async await 버전
    const 게시물가져오기 = () => {
        axios.get(watchAPI)
        .then((response) => {
        setProfile(response.data);
        console.log("프로필 가져오기 : " + response.data);//profile - response.data변경
        });
    };

    //닉네임 수정하기
    const 수정기능 = (p) => {
        setUserId(p.userId);//수정할 사용자id 설정
        setUsername(p.username);

    }
    return(
    <div>
        <h1>프로필 이미지 업로드</h1>
        {/*
        <input type="file" multiple onChange={(e) => setFiles(e.target.files)}/>
        <input type="file" multiple onChange={파일변경기능} />
        */}
        <div className="profile-thumbnail">
            {files.length > 0 && files.map((file,index) => (
                <div key={index}>
                    <img
                    src={URL.createObjectURL(file)}
                    />
                </div>
            ))}
        </div>
        <input type="file"  
        onChange={파일변경기능}/>
        <input 
        type="text" 
        placeholder="닉네임을 입력하세요." 
        value={username} 
        onChange={(e) => setUsername(e.target.value)} />
        <button onClick={이미지업로드3}>프로필저장하기</button>
        <hr/>
        <h3>프로필 상세페이지</h3>
        <div>
            {profile.length > 0 && profile.map((p) => (
                <div key={p.userId}>
                    <p>{p.username}</p>
                    <p>{p.createdAt}</p>
                    {p.profileImageUrl && 
                    p.profileImageUrl.split(',').map((image) => (
                        <img 
                        key={image} 
                        src={`http://localhost:9007/images/${image}`}
                        />
                    ))}
                    <button /*onClick={수정기능}*/ >프로필 이미지, 닉네임 변경하기</button>
                </div>

            ))}
        </div>
    </div>
    );

}
export default Profile;