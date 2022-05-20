function calc(){
    let food = ["food_1","food_2","food_3","food_4","food_5","food_6","food_7","food_8","food_9","food_10","food_11","food_12"];
    let total  = 0;
    for (let i = 0;i<12;i++){
        let a = document.getElementById(food[i]).innerHTML;
        let b = document.getElementById(food[i]+"_q").value;
        alert(a + " " + b);
    }

}
calc();