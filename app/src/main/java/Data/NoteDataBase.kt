package Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Note::class ) , version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun Notedao (): NoteDao

    companion object{  // singelton pattern -> means make one instance in application
        @Volatile  // make INSTANCE shown by all threads
        private var INSTANCE : NoteDataBase?= null

        fun getInstance(context:Context):NoteDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
               synchronized(this){ // to make the instance in one thread
                   val instance = Room.databaseBuilder(
                       context.applicationContext,
                       NoteDataBase::class.java,
                       "gemmy"
                   ).build()
                   INSTANCE = instance
                   return instance
               }

        }

    }

}