<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
   src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script>
   $(function() {
      $(document).ready(function() {
         /* Toggle menú de móviles  */
         $('#btn-drop').on('click', function(e) {
            e.preventDefault();
            $('#submenu').slideToggle(500);
         }); // fin click

         /* Hacer visible el menú al agrandar */
         $(window).resize(function() {
            if (innerWidth >= 480) {
               if ($('#submenu').css('display') == 'none') {
                  $('#submenu').removeAttr('style');
               }
            }
         }); // fin resize
         $('.genre').on('click', function() {
            var id = $(this).attr("id");
         });

         $('.new').each(function() {
            console.log("wkre");
            var page = location.href;
            var id = page.substring(page.length - 1, page.length);
            var url = $(this).attr("href");
            $(this).attr("href", url + id);
         });

      });

   })
</script>
<style>
html, body, ul, ol, li, form, fieldset, legend {
   margin: 0;
   padding: 0;
}

body {
   margin: 0;
   color: #000;
   background: #fff;
   /*font: 90%/1.3 "DejaVu Sans", "URW Gothic L", "Helvetica Neue", Helvetica,
      Arial, "Microsoft Sans Serif", sans-serif;*/
}

.clearfix:before, .clearfix:after {
   content: " ";
   display: table;
}

.clearfix:after {
   clear: both;
}

.clearfix {
   *zoom: 1;
}

.pagina {
   width: 80%;
   margin: auto;
   background-color: #eee;
}

.barra-menu {
   width: 100%;
   overflow: hidden;
   background-color: #666;
}

.menu {
   display: block;
   list-style: none;
   padding: 0;
   margin: 0;
   width: 100%;
   background-color: #333;
}

.lista-menu li {
   display: inline-block;
   float: left;
   width: 25%;
   border-right: 1px solid #7E7E7E;
   -webkit-box-sizing: border-box;
   -moz-box-sizing: border-box;
   box-sizing: border-box;
}

.lista-menu li:last-child {
   border-right: none;
}

.lista-menu a, .btn-menu {
   display: block;
   width: 100%;
   height: 3em;
   line-height: 3em;
}

.btn-menu {
   display: none;
}

}
.aln-izquierda {
   text-align: left;
}

.aln-centro {
   text-align: center;
}

.aln-derecha {
   text-align: right;
}

body {
   /*font-family: arial, helvetica, sans-serif;
   font-weight: normal;
   font-size: 100%;*/
   color: #333;
}

.lista-menu a, .btn-menu {
   text-decoration: none;
   color: #fff;
   background-color: #666;
}

.lista-menu a:hover {
   background-color: #505050;
}

@media screen and (max-width:480px) {
   #pagina {
      width: 100%;
   }
   .btn-menu {
      display: block;
      background-color: #333;
   }
   #submenu {
      display: none;
   }
   .lista-menu li {
      display: block;
      float: none;
      width: 100%;
      border-bottom: 1px solid #7E7E7E;
   }
}
</style>
<body>
<section id="contentSection">
   <div id="pagina">
      <div class="barra-menu">
         <a href="#" id="btn-drop" class="btn-menu aln-centro">Navegacion</a>

         <ul id="submenu" class="lista-menu aln-centro">
            <li><a href="genreMusicChart.do?genre_id=1" class="genre"
               id="1">댄스</a></li>
            <li><a href="genreMusicChart.do?genre_id=2" class="genre"
               id="2">발라드</a></li>
            <li><a href="genreMusicChart.do?genre_id=3" class="genre"
               id="3">팝/소울</a></li>
            <li><a href="genreMusicChart.do?genre_id=4" class="genre"
               id="4">클래식</a></li>
            <li><a href="genreMusicChart.do?genre_id=5" class="genre"
               id="5">힙합</a></li>
            <li><a href="genreMusicChart.do?genre_id=6" class="genre"
               id="6">락/메탈</a></li>
            <li><a href="genreMusicChart.do?genre_id=7" class="genre"
               id="7">O.S.T</a></li>
            <li><a href="genreMusicChart.do?genre_id=8" class="genre"
               id="8">기타</a></li>
         </ul>
      </div>
   </div>



   <h3 class="s_title01 mt25">최신앨범</h3>
   <div>

      <ul>
         <c:forEach var="dto" items="${album }" begin="1" end="5">
            <li>
               <div style="float: left; width: 20%; padding: 10px;">
                  <img width="170" height="170"
                     src="http://211.238.142.109:8080/album_img/${dto.album_art }.jpg" />
                  <dl>
                     <dt>
                        <strong class="none">앨범명</strong> <span class="ellipsis">
                           ${dto.album_name } </span>
                     </dt>
                     <dd>
                        <strong class="none">아티스트명</strong>
                           <span>${dto.music_artist }</span>
                     </dd>
                  </dl>
               </div>
            </li>
         </c:forEach>
      </ul>
   </div>

   <div id="pagina">
      <div class="barra-menu">
         <a href="#" id="btn-drop" class="btn-menu aln-centro">Navegacion</a>

         <ul id="submenu" class="lista-menu aln-centro">
            <li><a class="new" href="genreMusicChart.do?genre_id=">최신곡</a>
            </li>
            <li><a class="new" href="genreAlbumChart.do?genre_id=">최신앨범</a>
            </li>

         </ul>
      </div>
   </div>
      <table class="table table-hover">
      <thead>
         <tr>
            <th width=20%>순위</th>
            <th width=40%>앨범</th>
            <th width=20%>아티스트</th>
            <th width=20%>앨범정보</th>
         </tr>
      </thead>
      <tbody>
         <%
            int i = 1;
         %>
         <c:forEach var="vo" items="${chart }">

            <tr>
                  <td><span><%=i%></span><span>위</span></td>
               <td>
                  
                     <span class="thumb_frame"></span> <img width="104" height="104"
                        src="http://211.238.142.109:8080/album_img/${vo.album_art }.jpg" />
                  
                  <span><strong>${vo.album_type }</strong></span>   
                  <span>${vo.album_name }</span>
               </td>
               <td>
                  <span>${vo.album_artist }</span>
               </td>
               <td>
                  <strong>앨범 발매일</strong> <span><fmt:formatDate value="${vo.album_release }" 
            						pattern="yyyy.MM.dd" /></span>
                  <em>|</em> <strong>앨범 총 수록곡수</strong> <span>${vo.count }</span>
               </td>
            </tr>
            <%i++;%>
         </c:forEach>
      </tbody>
   </table>
</section>
</body>
</html>