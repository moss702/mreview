
  // 전역변수로 counter 초기값 : 0
  // 10 ** 3 : 10의 3제곱 : 1000
  // 어씽크 함수: 의도적으로 딜레이 줌

  let counter = 0;
  // function callLocal(n, cb) {
  //   const add = 10 ** n;
  //   counter += add;
  //   console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`);
  //   if(cb) cb();
  // }
  //
  // function callAsync(n, cb){
  //   const add = 10 ** n;
  //   const delay = Math.floor(Math.random() * 1000); // 0~999 난수
  //
  //   setTimeout(() => {
  //     counter += add;
  //     console.log(`[async] 10^${n} = ${add} -> counter : ${counter}, delay : ${delay}ms`);
  //     if(cb) cb();
  //   }, delay);
  // }

//==========================================================
  // // (() => {callAsync(0);}) ();
  // callAsync(0, () => {
  //   callAsync(1, () => {
  //     callAsync(2, () => {
  //       callAsync(3, () => {
  //         callAsync(4, () => {
  //           callLocal(5, () => {
  //             callLocal(6, () => {
  //               callLocal(7, () => {
  //                 callLocal(8, () => {
  //                   callLocal(9, () => {
  //                     console.log("마지막!")
  //                   })
  //                 })
  //               })
  //             })
  //           })
  //         })
  //       })
  //     })
  //   })
  // });
  // ========================== Promise
  // const promise = new Promise((resolve, reject) => {
  //   // if(성공) resolve(결과);
  //   // else(실패) reject(에러);
  // });
  //
  // // promise
  // //     .then(result => { /*성공시*/ })
  // //     .catch(e => reject(e) /*실패시*/ )
  // //     .finally(() => { /*무조건 실행*/ })
  // // [].sort((a, b) => { });
  //
  // function asyncTask() {
  //   return new Promise((resolve) => {
  //     setTimeout(() => {resolve("완료")}, 500);
  //   });
  // }
  // const result = asyncTask();
  // result
  //     .then(msg => {
  //       console.log(msg)
  //     })
  //     .catch(err => {
  //       console.log(err)
  //     });
  // // Thenable function
  // asyncTask().then(console.log).catch(console.log);

  // ========================== Promise 2
  function callLocal(n) {
    return new Promise((resolve) => {
      const add = 10 **  n;
      counter += add;
      console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`);
      resolve();
    });
  }
  function callAsync(n) {
  return new Promise(resolve => {

  const add = 10 ** n;
  const delay = Math.floor(Math.random() * 1000); // 0~999 사이의 난수

  setTimeout(() => {
  counter += add;
  console.log(`[async] 10^${n} = ${add} -> counter : ${counter}, delay : ${delay}ms`);
  resolve();
} , delay);
});
}
  // callAsync(0)
  //     .then(() => callAsync(1))
  //     .then(() => callAsync(2))
  //     .then(() => callAsync(3))
  //     .then(() => callAsync(4))
  //     .then(() => callLocal(5))
  //     .then(() => callLocal(6))
  //     .then(() => callLocal(7))
  //     .then(() => callLocal(8))
  //     .then(() => callLocal(9))
  //     .then(() => console.log("마지막"));
  // then 호출안하면? thenable function이 없다는 에러 표시

  // ============= async await =================================
  async function run() {
  await callAsync(0);
  await callAsync(1);
  await callAsync(2);
  await callAsync(3);
  await callAsync(4);
  await callLocal(5);
  await callLocal(6);
  await callLocal(7);
  await callLocal(8);
  console.log("마지막 직전! await 이후");
  await callLocal(9);
  console.log("마지막! await 이후");
}
  // run();
  //=============== fetch ==============================
  function  fetchWithCallback(url= "", callback) {
  fetch(url)
  .then(response => response.json())
  .then(data => {
  console.log("콜백 결과", url)
  console.log(data)
  if(callback) callback(); //만약 콜백 존재시 콜백 실행
})
}
  // fetchWithCallback("../replies/board/10");
  // fetchWithCallback("../replies/board/11");
  // fetchWithCallback("../replies/board/12");
  // 얘네는 순서 보장 안됨. 새로고침할때마다 변함

  fetchWithCallback("../replies/board/10", () => fetchWithCallback("../replies/board/11"), () => fetchWithCallback("../replies/board/12"));
  // 얘는 새로고침 몇번 해도 동일 순서
  //=============== fetch2 ==============================
  function  fetchReplies(bno) {
  return  fetch(`../replies/board/${bno}`)
  .then(response => response.json())
  .then(data => {
  console.log("콜백 결과", bno)
  console.log(data)
})
}
  // fetchReplies(1)
  //     .then(() => fetchReplies(2))
  //     .then(() => fetchReplies(3))
  //     .then(() => console.log("promise call 적용"))
  (async () => {
  await fetchReplies(1);
  await fetchReplies(2);
  await fetchReplies(3);
  console.log("IIFE + Await Call")
})(); // IIFE

  //============== ajax =========================================
  // $.ajax({
  //   success: (data) => {
  //     $.ajax({
  //       success: (data) => {
  //
  //       }
  //     })
  //   }
  // })
  // $.ajax({
  //   success: (data) => {},  // 여기서의 리턴값이 아래 녀석들의 값이 된다. == jQuery 스타일의 체인
  //   error: (error) => {},
  //   finally: (data) => {}
  // })
  // .done((data) => {
  //
  // })
  // .fail(error => {
  //
  // })
  // .always((data) => {
  //
  // })
  // .done((data) => {
  //
  // })