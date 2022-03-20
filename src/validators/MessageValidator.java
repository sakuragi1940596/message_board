package validators;

import java.util.ArrayList;
import java.util.List;

import models.Message;

public class MessageValidator {
    // バリデーションを実行する
    //String型の配列を戻り値のデータ型として、バリデーションを実行するメソッドであるvalidateメソッドを作成。引数はMessage mとする。
    //
    public static List<String> validate(Message m) {
        //String型を指定した配列errorsを宣言し、Arraylistをインスタンス化する
        List<String> errors = new ArrayList<String>();
        //title_error変数を定義し、""と同じであれば、errors配列にtitle_errorを加える
        String title_error = validateTitle(m.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = validateContent(m.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }
        //このメソッドの呼び出し先の戻り値としてerrorsを返す。
        //つまり、このerrorsに配列要素が加えられているならば、エラーが発生していることになり、下記の対応するメソッドが実行される。
        return errors;
    }

    // タイトルの必須入力チェック
    private static String validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
        }

        return "";
    }

    // メッセージの必須入力チェック
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "メッセージを入力してください。";
        }

        return "";
    }
}