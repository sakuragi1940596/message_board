package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //String型の変数_tokenを宣言。右辺のgetParameterメソッドによってリクエストを戻り値として取得し、左辺で参照
            String _token = request.getParameter("_token");
            //_tokenがnullではないかつ上記で取得したリクエストパラメーターが、sessionIDと同じなら、スコープ内の処理が実行される。
            if(_token != null && _token.equals(request.getSession().getId())) {
                //まず、DTOであるDBUtilクラスのcreateメソッド（静的メソッド）を左辺のemが参照。
                EntityManager em = DBUtil.createEntityManager();

                // セッションスコープからメッセージのIDを取得。editサーブレットでセッションスコープにIDをsetAttributeしているから可能。
                //スコープの値は汎用的なobject型に変換されているため、Integer型へキャストして実数として使用。
                // 該当のIDのメッセージ1件のみをデータベースから取得
                Message m = em.find(Message.class, (Integer)(request.getSession().getAttribute("message_id")));

                // フォームの内容を各フィールドに上書き
                String title = request.getParameter("title");
                m.setTitle(title);

                String content = request.getParameter("content");
                m.setContent(content);

                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                m.setUpdated_at(currentTime);       // 更新日時のみ上書き

                // データベースを更新
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                // セッションスコープ上の不要になったデータを削除
                request.getSession().removeAttribute("message_id");

                // indexページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }
