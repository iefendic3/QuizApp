package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {
    //Istrazivanje
    @GET("/istrazivanje")
    suspend fun getIstrazivanja(@Query("offset") offset : Int) : Response<List<Istrazivanje>>

    @GET("/istrazivanje/{id}")
    suspend fun getIstrazivanjeById(@Path("id") idIstrazivanja : Int) : Response<Istrazivanje>

    @GET("/grupa/{gid}/istrazivanje")
    suspend fun getIstrazivanjaByGrupaId(@Path("gid") grupaId: Int) : Response<List<Istrazivanje>>

    //Grupa
    @GET("/anketa/{id}/grupa")
    suspend fun getGrupeByAnketaId(@Path("id") anketaId: Int) : Response<List<Grupa>>

    @POST("/grupa/{gid}/student/{id}")
    suspend fun dodajStudentaUGrupuById(@Path("gid") grupaId: Int, @Path("id") studentId: String) : Response<MessageResponse>

    @GET("/student/{id}/grupa")
    suspend fun getUpisaneGrupeById(@Path("id") hashStudenta: String) : Response<List<Grupa>>

    @GET("/student/{id}/grupa")
    suspend fun getGrupeStudenta(@Path("id") hashStudenta: String) : Response<List<Grupa>>

    @GET("/grupa")
    suspend fun getGrupe() : Response<List<Grupa>>

    @GET("/grupa/{id}")
    suspend fun getGrupeById(@Path("id") grupaId: Int): Response<Grupa>

    //Anketa
    @GET("/anketa")
    suspend fun getAnkete(@Query("offset") offset : Int) : Response<List<Anketa>>

    @GET("/anketa")
    suspend fun getAll() : Response<List<Anketa>>

    @GET("/anketa/{id}")
    suspend fun getAnketaById(@Path("id") anketaId: Int) : Response<Anketa>

    @GET("/grupa/{id}/ankete")
    suspend fun getAnketeByGrupaId(@Path("id") grupaId : Int) : Response<List<Anketa>>

    //Odgovor
    @GET("/student/{id}/anketataken/{ktid}/odgovori")
    suspend fun getOdgovoriById(@Path("id") hashStudenta: String, @Path("ktid") pokusajId: Int) : Response<List<Odgovor>>

    @POST("/student/{id}/anketataken/{ktid}/odgovor")
    suspend fun dodajOdgovorById(@Path("id") hashStudenta: String, @Path("ktid") pokusajId: Int, @Body odgovorPitanje: OdgovorPitanjeBodovi) : Response<Odgovor>

    //AnketaTaken
    @GET("/student/{id}/anketataken")
    suspend fun getPoceteAnkete(@Path("id") hashStudenta: String) : Response<List<AnketaTaken>>

    @POST("/student/{id}/anketa/{kid}")
    suspend fun zapocniAnketu(@Path("id") idStudenta: String, @Path("kid") idAnkete: Int) : Response<AnketaTaken>

    //Account
    @GET("/student/{id}")
    suspend fun getHash(@Path("id") studentId:String): Response<Account>

    @DELETE("/student/{id}/upisugrupeipokusaji")
    suspend fun obrisiKorisnikaById(@Path("id") hashStudenta: String) : Response<MessageResponse>

    //Pitanje
    @GET("/anketa/{id}/pitanja")
    suspend fun getPitanjaByAnketaId(@Path("id") anketaId:Int): Response<List<Pitanje>>


















}