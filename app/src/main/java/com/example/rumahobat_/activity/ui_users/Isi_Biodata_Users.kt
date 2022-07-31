package com.example.rumahobat_.activity.ui_users

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import com.example.rumahobat_.BuildConfig
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Home_Main
import com.example.rumahobat_.activity.Profil_Home
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_Input_Data_Users
import com.example.rumahobat_.value.Value_lihatDataUsers
import kotlinx.android.synthetic.main.isi_biodata__users.*
import kotlinx.android.synthetic.main.isi_biodata__users.beratBadan
import kotlinx.android.synthetic.main.isi_biodata__users.jenisKelamin
import kotlinx.android.synthetic.main.isi_biodata__users.profil_image
import kotlinx.android.synthetic.main.isi_biodata__users.tempatLahir
import kotlinx.android.synthetic.main.isi_biodata__users.tinggiBadan
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger


class Isi_Biodata_Users : AppCompatActivity() {
//    companion object{
//        const val KEY_ID_CHAT = "key_id_chat"
//    }
    lateinit var session : SessionManager
    lateinit var terimaIdUsersBiodata : String
    lateinit var terimaRadio : String
    lateinit var radioGroup : RadioGroup
    lateinit var radioLakiLaki : RadioButton
    lateinit var radioPerempuan : RadioButton
    lateinit var botton_Selanjutnya : Button
    var booleann : Boolean = false
    var selectedId: Int = 0
    lateinit var id_akunInputDataUsers : String

    lateinit var tampung_nama_profil_users: String
    lateinit var tampung_tempatLahir: String
    lateinit var tampung_tanggal_lahir: String
    lateinit var tampung_jenisKelamin: String
    lateinit var tampung_beratBadan: String
    lateinit var tampung_tinggiBadan: String

    lateinit var tampung_alamatt: String
    lateinit var tampung_riwayatObat: String
    lateinit var tampung_riwayatPenyakit: String


//    upload foto variabel
    private val mMediaUri: Uri? = null
    private var fileUri: Uri? = null
    private var mediaPath: String? = null
    private val btnCapturePicture: Button? = null
    private var mImageFileLocation = ""
    var pDialog: ProgressDialog? = null
    private var postPath: String? = null
    lateinit var namaSession : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isi_biodata__users)
        session = SessionManager(this)
        namaSession = session.nama.toString()

        beckIsiChatingan.setOnClickListener {
            var intent = Intent(this, Home_Main::class.java)
            startActivity(intent)
            finish()
        }

//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.CAMERA
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
////            Toast.makeText(applicationContext, "tidak berhasil diizinkan", Toast.LENGTH_LONG).show()
////            ambilGambar.setEnabled(false)
////            botton_kirim.setEnabled(false)
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//                ),
//                0
//            )
//        } else {
////            Toast.makeText(applicationContext, " berhasil diizinkan", Toast.LENGTH_LONG).show()
////            image.setEnabled(true)
////            video.setEnabled(true)
////            pdf.setEnabled(true)
//        }

//            ambilGambar.setEnabled(false)
//            botton_kirim.setEnabled(false)
//        text_nohp.text = session.nomor
//        nama_users.text = session.nama

        id_akunInputDataUsers = session.id.toString()

//        tanggal_lahir
//        berat_badan
//        tinggi_badan



        botton_kirim.setOnClickListener {
             tampung_nama_profil_users = nama_profil_users.text.toString().trim()
             tampung_tempatLahir = tempatLahir.text.toString().trim()
             tampung_tanggal_lahir    = tanggal_lahir.text.toString().trim()
             tampung_jenisKelamin = jenisKelamin.text.toString().trim()
             tampung_beratBadan = beratBadan.text.toString().trim()
             tampung_tinggiBadan = tinggiBadan.text.toString().trim()



            tampung_alamatt = alamatt.text.toString().trim()
            tampung_riwayatObat = riwayatObat.text.toString().trim()
            tampung_riwayatPenyakit = riwayatPenyakit.text.toString().trim()

            var boolean : Boolean = false

            if (tampung_alamatt.isEmpty()){
                boolean = true
                alamatt.error = "Form Kosong, Harap Isi"
            }

            if (tampung_riwayatObat.isEmpty()){
                boolean = true
                riwayatObat.error = "Form Kosong, Harap Isi"
            }

            if (tampung_riwayatPenyakit.isEmpty()){
                boolean = true
                riwayatPenyakit.error = "Form Kosong, Harap Isi"
            }


            if (tampung_nama_profil_users.isEmpty()){
                boolean = true
                nama_profil_users.error = "Form Kosong, Harap Isi"
            }

            if (tampung_tempatLahir.isEmpty()){
                boolean = true
                tempatLahir.error = "Form Kosong, Harap Isi"
            }

            if (tampung_tanggal_lahir.isEmpty()){
                boolean = true
                tanggal_lahir.error = "Form Kosong, Harap Isi"
            }

            if (tampung_jenisKelamin.isEmpty()){
                boolean = true
                jenisKelamin.error = "Form Kosong, Harap Isi"
            }

            if (tampung_beratBadan.isEmpty()){
                boolean = true
                beratBadan.error = "Form Kosong, Harap Isi"
            }

            if (tampung_tinggiBadan.isEmpty()){
                boolean = true
                tinggiBadan.error = "Form Kosong, Harap Isi"
            }

            if (!boolean){
                uploadFile()
            }




        }


        ambilGambar.setOnClickListener {
            MaterialDialog.Builder(this)
                .items(R.array.uploadImages)
                .itemsIds(R.array.itemIds)
                .itemsCallback { dialog, view, which, text ->
                    when (which) {
                        0 -> {
                            val galleryIntent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                            startActivityForResult(
                                galleryIntent,
                                REQUEST_PICK_PHOTO
                            )
                        }
                        1 -> captureImage()
                        2 -> profil_image!!.setImageResource(R.drawable.icontambprofil)
                    }
                }
                .show()
        }


//        calderTanggalLahir()
        jenisPopUp.setOnClickListener {
            dialogPopUpJenisKelamin()
        }



        dateLahir()
        initDialog()
        lihatDataUsers()

    }


    private fun lihatDataUsers() {
        var id_penilaian = session.id.toString()
        var status_akun_users = "tidak"
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_lihatDataUsers>
        call = gsonRetrofit.get_lihatDataUsers(id_penilaian)
        call.enqueue(object : Callback<Value_lihatDataUsers> {
            override fun onFailure(call: Call<Value_lihatDataUsers>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi lihat teman", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_lihatDataUsers>,
                response: Response<Value_lihatDataUsers>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){

                    val responsePenilaian = dataLogin?.response?.get(0)
                    var terima_nama_profil   = responsePenilaian?.nama_panjang_users
                    var terima_nomorHp = responsePenilaian?.no_hp
                    var terima_usia = responsePenilaian?.usia
                    var terima_tempatLahir   = responsePenilaian?.tempat_lahir
                    var terima_dateLahir   = responsePenilaian?.date_lahir
                    var terima_jenisKelamin   = responsePenilaian?.jenis_kelamin
                    var terima_beratBadan = responsePenilaian?.berat_badan
                    var terima_tinggiBadan = responsePenilaian?.tinggi_badan
                    var terima_riwayat_obat = responsePenilaian?.riwayat_obat
                    var terima_riwayat_penyakit = responsePenilaian?.riwayat_penyakit
                    var terima_alamat = responsePenilaian?.alamat


                    nama_profil_users.setText(terima_nama_profil)
                    tempatLahir.setText(terima_tempatLahir)
                    tanggal_lahir.text = terima_dateLahir
                    jenisKelamin.text = terima_jenisKelamin
                    beratBadan.setText(terima_beratBadan)
                    tinggiBadan.setText(terima_tinggiBadan)

                    riwayatObat.setText(terima_riwayat_obat)
                    riwayatPenyakit.setText(terima_riwayat_penyakit)
                    alamatt.setText(terima_alamat)
                    Glide.with(applicationContext)
                        .load(Api_Interface.KEY_URL_FOTO_USERS+responsePenilaian?.foto_users).into(profil_image)

//                    Toast.makeText(applicationContext, responsePenilaian?.foto_users ,Toast.LENGTH_LONG).show()
//                    Glide.with(applicationContext)
//                        .load(Api_Interface.KEY_URL_FOTO_USERS+responsePenilaian?.foto_users).into(profil_image)
//                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11

                }else
                {
                    nama_profil_users.setText(namaSession)
                    Toast.makeText(applicationContext, "Silahkan Lengkapi Data Anda", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun inputDatausersTanpaFoto() {
        var id_penilaian = session.id.toString()
        var status_akun_users = "tidak"

        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_Input_Data_Users>
        call = gsonRetrofit.get_inputDatausers(

            id_penilaian,
            tampung_tinggiBadan,
            tampung_tanggal_lahir,
            tampung_beratBadan,
            tampung_jenisKelamin,
            tampung_tempatLahir,
            tampung_nama_profil_users,

            tampung_alamatt,
            tampung_riwayatObat,
            tampung_riwayatPenyakit

        )
        call.enqueue(object : Callback<Value_Input_Data_Users> {
            override fun onFailure(call: Call<Value_Input_Data_Users>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi lihat teman", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_Input_Data_Users>,
                response: Response<Value_Input_Data_Users>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                    nama_profil_users.setText("")
                    tempatLahir.setText("")
                    tanggal_lahir.setText("")
                    jenisKelamin.setText("")
                    beratBadan.setText("")
                    tinggiBadan.setText("")
                    profil_image!!.setImageResource(R.drawable.icontambprofil)

                    var intent = Intent(this@Isi_Biodata_Users, Profil_Home::class.java)
                    startActivity(intent)
                    finish()
                }else
                {
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun dateLahir() {
//        terimaIdUsersBiodata = intent.getStringExtra(Chat_Apoteker.KEY_ID_CHAT).toString()
//        Toast.makeText(applicationContext,terimaIdUsersBiodata, Toast.LENGTH_SHORT).show()
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]

//        tanggal_lahir.text = day.toString()+"/"+(month+1) +"/"+ year
        showCalender.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    tanggal_lahir.setText(
                        dayOfMonth.toString() + "/" + (month + 1) + "/" + year
                    )
                },
                day,
                month,
                year
            )
            datePickerDialog.show()
        }
    }


    private fun dialogPopUpJenisKelamin() {
        val openDialog = Dialog(this)
        openDialog.setContentView(R.layout.pop_up_jenis_kelamin)

        radioGroup = openDialog.findViewById(R.id.radioGroup) !!
        radioLakiLaki = openDialog.findViewById(R.id.radioLakiLaki) !!
        radioPerempuan = openDialog.findViewById(R.id.radioPerempuan) !!
        botton_Selanjutnya =  openDialog.findViewById(R.id.botton_Selanjutnya) !!
        openDialog.setCancelable(true)
        botton_Selanjutnya.setOnClickListener {
            val selectedId: Int = radioGroup.getCheckedRadioButtonId()

            if (selectedId == radioLakiLaki.getId()) {
                jenisKelamin.text = radioLakiLaki.getText().toString()
            } else if (selectedId == radioPerempuan.getId()) {
                jenisKelamin.text = radioPerempuan.getText().toString()
            }
            openDialog.dismiss()
        }


//         = terimaRadio

//        Toast.makeText(applicationContext, terima_status_namaPanjang, Toast.LENGTH_LONG).show()
//
//        penilaian_puass.setOnClickListener {
//            Toast.makeText(applicationContext, "suka", Toast.LENGTH_LONG).show()
//            updateDataPenilaianSuka()
//            openDialog.dismiss()
//        }
//        penilaian_puastidak.setOnClickListener {
//            Toast.makeText(applicationContext, "tidak suka", Toast.LENGTH_LONG).show()
//            updateDataPenilaianTidakSuka()
//            openDialog.dismiss()
//        }



//        dialog!!.setIcon(R.mipmap.ic_launcher)
//        dialog!!.setTitle("Form Biodata")
//        dialog!!.setPositiveButton("SUBMIT") { dialog, which ->
////            nama = txt_nama!!.text.toString()
////            usia = txt_usia!!.text.toString()
////            alamat = txt_alamat!!.text.toString()
////            status = txt_status!!.text.toString()
////            txt_hasil!!.text = "Nama : $nama\nUsia : $usia\nAlamat : $alamat\nStatus : $status"
//
//            dialog.dismiss()
//            dialog.dismiss()
//        }
//        dialog!!.setNegativeButton("CANCEL") { dialog, which -> dialog.dismiss() }
        openDialog!!.show()
    }



    //    fun calderTanggalLahir(){
//        //deklarasi widget yang ada di layout activity_main
//
//        //deklarasi widget yang ada di layout activity_main
//        calnderTanggalLahir.state().edit()
//            .setFirstDayOfWeek(Calendar.WEDNESDAY)
//            .setMinimumDate(CalendarDay.from(1900, 1, 1))
//            .setMaximumDate(
//                CalendarDay.from(
//                    2045,
//                    12,
//                    31
//                )
//            ) // Maksud dari MONTHS adalah calender tersebut akan tampil berbentuk 4 minggu atau 1 bulan
//            // jika calendar mode tersebut di ganti menjadi WEEKS maka akan yang tampil akan 1 minggu
//            .setCalendarDisplayMode(CalendarMode.MONTHS)
//            .commit()
//
//        calnderTanggalLahir.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected -> //menampilkan toast jika berhasil di klik
//            Toast.makeText(this, "" + date, Toast.LENGTH_SHORT).show()
//        })
//    }
override fun onBackPressed() {
    super.onBackPressed()
    val intent = Intent(applicationContext, Profil_Home::class.java)
    startActivity(intent)
    finish()
}


//    ambil gambar dari storage

    private val isExternalStorageAvailable: Boolean
        private get() {
            val state = Environment.getExternalStorageState()
            return if (Environment.MEDIA_MOUNTED == state) {
                true
            } else {
                false
            }
        }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Get the Image from data
                    val selectedImage = data.data
                    val filePathColumn =
                        arrayOf(MediaStore.Images.Media.DATA)
                    val cursor =
                        contentResolver.query(selectedImage, filePathColumn, null, null, null)!!
                    cursor.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    mediaPath = cursor.getString(columnIndex)
                    // Set the Image in ImageView for Previewing the Media
                    profil_image!!.setImageBitmap(BitmapFactory.decodeFile(mediaPath))
                    cursor.close()
                    postPath = mediaPath
                }
            } else if (requestCode == CAMERA_PIC_REQUEST) {
                postPath = if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(profil_image!!)
                    mImageFileLocation
                } else {
                    Glide.with(this).load(fileUri).into(profil_image!!)
                    fileUri!!.path
                }
            }
        } else if (resultCode != Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Sorry, there was an error!", Toast.LENGTH_LONG).show()
        }
    }// no camera on this device// this device has a camera

    /**
     * Checking device has camera hardware or not
     */
    private val isDeviceSupportCamera: Boolean
        private get() = if (applicationContext.packageManager.hasSystemFeature(
                PackageManager.FEATURE_CAMERA
            )
        ) {
            // this device has a camera
            true
        } else {
            // no camera on this device
            false
        }

    protected fun initDialog() {
        pDialog = ProgressDialog(this)
        pDialog!!.setMessage("Loading...")
        pDialog!!.setCancelable(true)
    }

    protected fun showpDialog() {
        if (!pDialog!!.isShowing) pDialog!!.show()
    }

    protected fun hidepDialog() {
        if (pDialog!!.isShowing) pDialog!!.dismiss()
    }

    /**
     * Launching camera app to capture image
     */
    private fun captureImage() {
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            val callCameraApplicationIntent = Intent()
            callCameraApplicationIntent.action = MediaStore.ACTION_IMAGE_CAPTURE

            // We give some instruction to the intent to save the image
            var photoFile: File? = null
            try {
                // If the createImageFile will be successful, the photo file will have the address of the file
                photoFile = createImageFile()
                // Here we call the function that will try to catch the exception made by the throw function
            } catch (e: IOException) {
                Logger.getAnonymousLogger()
                    .info("Exception error in generating the file")
                e.printStackTrace()
            }
            // Here we add an extra file to the intent to put the address on to. For this purpose we use the FileProvider, declared in the AndroidManifest.
            val outputUri = FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID + ".provider",
                photoFile!!
            )
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri)

            // The following is a new line with a trying attempt
            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
            Logger.getAnonymousLogger().info("Calling the camera App by intent")

            // The following strings calls the camera app and wait for his file in return.
            startActivityForResult(
                callCameraApplicationIntent,
                CAMERA_PIC_REQUEST
            )
        } else {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)

            // start the image capture Intent
            startActivityForResult(intent, CAMERA_PIC_REQUEST)
        }
    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        Logger.getAnonymousLogger().info("Generating the image - method started")

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        val timeStamp =
            SimpleDateFormat("yyyyMMdd_HHmmSS").format(Date())
        val imageFileName = "IMAGE_$timeStamp"
        // Here we specify the environment location and the exact path where we want to save the so-created file
        val storageDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app")
        Logger.getAnonymousLogger().info("Storage directory set")

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir()

        // Here we create the file using a prefix, a suffix and a directory
        val image = File(storageDirectory, "$imageFileName.jpg")
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set")
        mImageFileLocation = image.absolutePath
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image
    }

    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri")
    }
    /**
     * Receiving activity result method will be called after closing the camera
     */
    /**
     * ------------ Helper Methods ----------------------
     */
    /**
     * Creating file uri to store image/video
     */
    fun getOutputMediaFileUri(type: Int): Uri {
        return Uri.fromFile(getOutputMediaFile(type))
    }

//    val gsonRetrofit = Gson_Retrofit.createRetrofit()
//    val call :Call<Value_Input_Data_Users>
//    call = gsonRetrofit.upload("token", map)

    // Uploading Image/Video
    private fun uploadFile() {



Toast.makeText(applicationContext, id_akunInputDataUsers, Toast.LENGTH_LONG).show()
//
//        Toast.makeText(applicationContext, id_akunInputDataUsers +" ,"+
//                tampung_nama_profil_users +" ,"+
//                tampung_tempatLahir +" ,"+
//                tampung_tanggal_lahir +" ,"+
//                tampung_jenisKelamin +" ,"+
//                tampung_beratBadan +" ,"+
//                tampung_tinggiBadan
//            , Toast.LENGTH_LONG
//        ).show()




        if (postPath == null || postPath == "") {
//            Toast.makeText(this, "gambar tidak ada disini terupload", Toast.LENGTH_LONG).show()
            inputDatausersTanpaFoto()



            return
        } else {
            showpDialog()

            // Map is used to multipart the file using okhttp3.RequestBody
            val map: MutableMap<String, RequestBody> =
                HashMap()
            val file = File(postPath)

            // Parsing any Media type file
            val requestBody = RequestBody.create("*/*".toMediaTypeOrNull(), file)
            map["file\"; filename=\"" + file.name + "\""] = requestBody

            val requestBodyid_akunInputDataUsers = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), id_akunInputDataUsers)
            val requestBodyid_tampung_tinggiBadan = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_tinggiBadan)
            val requestBodyid_tampung_beratBadan = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_beratBadan)
            val requestBodyid_tampung_tanggal_lahir = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_tanggal_lahir)

            val requestBodyid_tampung_jenisKelamin = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_jenisKelamin)
            val requestBodyid_tampung_tempatLahir = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_tempatLahir)
            val requestBodyid_tampung_nama_profil_users = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_nama_profil_users)

            val requestBodyid_tampung_alamatt = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_alamatt)
            val requestBodyid_tampung_riwayatObat = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_riwayatObat)
            val requestBodyid_tampung_riwayatPenyakit = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), tampung_riwayatPenyakit)


            map["file\"; filename=\"" + file.name + "\""] = requestBody

            val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
            val call :Call<Value_Input_Data_Users?>?
            call = gsonRetrofit.upload("token", map,



                requestBodyid_akunInputDataUsers,
                requestBodyid_tampung_tinggiBadan,
                requestBodyid_tampung_tanggal_lahir,
                requestBodyid_tampung_beratBadan,
                requestBodyid_tampung_jenisKelamin,
                requestBodyid_tampung_tempatLahir,
                requestBodyid_tampung_nama_profil_users,
                requestBodyid_tampung_alamatt,
                requestBodyid_tampung_riwayatObat,
                requestBodyid_tampung_riwayatPenyakit

                                      )
//            call = gsonRetrofit.upload(id_akunInputDataUsers,"token", map)

            call?.enqueue(object : Callback<Value_Input_Data_Users?>{
                override fun onFailure(call: Call<Value_Input_Data_Users?>, t: Throwable) {
                    Toast.makeText(applicationContext, "Tidak Koneksi", Toast.LENGTH_LONG).show()
                    hidepDialog()
                }

                override fun onResponse(call: Call<Value_Input_Data_Users?>, response: Response<Value_Input_Data_Users?>) {
                    val dataLogin = response.body()
//                items = dataLogin?.response as MutableList<Model_Login>


                    val value = dataLogin?.value
                    val pesan = dataLogin?.pesan
//                    val response = dataLogin?.response?.get(0)
                        if (value.equals("1")){
                            hidepDialog()
                            Toast.makeText(applicationContext,pesan, Toast.LENGTH_LONG).show()
                            nama_profil_users.setText("")
                            tempatLahir.setText("")
                            tanggal_lahir.setText("")
                            jenisKelamin.setText("")
                            beratBadan.setText("")
                            tinggiBadan.setText("")
                            profil_image!!.setImageResource(R.drawable.icontambprofil)
                            var intent = Intent(this@Isi_Biodata_Users, Profil_Home::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            hidepDialog()
                            Toast.makeText(applicationContext,pesan, Toast.LENGTH_LONG).show()
                        }

                    }

            })
        }
    }

    companion object {
        const val KEY_ID_CHAT = "key_id_chat"
        private const val REQUEST_TAKE_PHOTO = 0
        private const val REQUEST_PICK_PHOTO = 2
        private const val CAMERA_PIC_REQUEST = 1111
        private val TAG = Isi_Biodata_Users::class.java.simpleName
        private const val CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100
        const val MEDIA_TYPE_IMAGE = 1
        const val IMAGE_DIRECTORY_NAME = "Android File Upload"

        /**
         * returning image / video
         */
        private fun getOutputMediaFile(type: Int): File? {

            // External sdcard location
            val mediaStorageDir = File(
                Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME
            )

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d(
                        TAG, "Oops! Failed create "
                                + IMAGE_DIRECTORY_NAME + " directory"
                    )
                    return null
                }
            }

            // Create a media file name
            val timeStamp = SimpleDateFormat(
                "yyyyMMdd_HHmmss",
                Locale.getDefault()
            ).format(Date())
            val mediaFile: File
            mediaFile = if (type == MEDIA_TYPE_IMAGE) {
                File(
                    mediaStorageDir.path + File.separator
                            + "IMG_" + ".jpg"
                )
            } else {
                return null
            }
            return mediaFile
        }
    }


}