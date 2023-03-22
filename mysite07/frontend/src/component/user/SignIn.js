import React,{ useState }  from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/SignIn.scss';

const SignIn = () => {
  
    const login = async (e) => {
        e.preventDefault();
        const data = {
            "email":e.target.email.value,
            "password":e.target.password.value   
         };
        try {
            const response = await fetch(`/api/user/login`, {
              method: 'post',
              headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
              },
              body: JSON.stringify(data)
            });
      
            if(!response.ok) {
              throw new Error(`${response.status} ${response.statusText}`);
            }
      
            const json = await response.json();
      
            if(json.result !== 'success') {
              throw new Error(`${json.result} ${json.message}`);  
            }
            console.log(json);
            //document.location.href = '/'

          } catch(err) {
            console.log(err);
          }
    }

    return (
        <MySiteLayout>
            <div className={styles.SignIn}>
                <form onSubmit={login}>
                    <label className="block-label">이메일</label>
					<input id="email" name="email" type="text" />
					<label className="block-label">패스워드</label>
					<input name="password" type="password"/>
					<input type="submit" value="로그인"/>
                </form>
            </div>
        </MySiteLayout>
    );
};

export default SignIn;