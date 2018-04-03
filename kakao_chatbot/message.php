<?php
	/*get key be inputed by keyboar(user_key, type, content)*/
	$data = json_decode(file_get_contents('php://input'),true)
	/*save data */
	$content = $data["content"];
	switch ($content) {
		case "1. 보험 안내":
			echo '{
				"message" : 
				{
					"text" : "보험 종류~~~"
				}
				"keyboard":
				{
					"type" : "buttons",
					"buttons" : ["이브이","파라다스","테리어몬"]
				}
			}';
			break;
		case "2. 보험 가입 조건":
			echo '{
				"message" : 
				{
					"text" : "가입하고 싶니?"
				}
				"keyboard":
				{
					"type" : "buttons",
					"buttons" : ["너 누구야","이름을","말해봐"]
				}
			}';
			break;

		default:
			echo '
			{
				"message" : 
				{
					"text" : "잘못 누른 경우.."
				}
				"keyboard":
				{
					"type" : "buttons",
					"buttons" : [1. 보험 안내", "2. 보험 가입 조건","3. 보험 약관"]
				}
			}';
			break;
	}
?>