package nseki.com.app.samplepagingroom.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import nseki.com.app.samplepagingroom.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val randomStringAdapter = RandomStringAdapter { randomString ->
            val randomStringScoreIncremented = randomString.copy(
                score = randomString.score + 1
            )
            viewModel.update(randomStringScoreIncremented)
        }

        binding.randomStringList.apply {
            adapter = randomStringAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        viewModel.randomStrings.observe(
            this,
            Observer { pagingData -> randomStringAdapter.submitData(lifecycle, pagingData) }
        )
    }
}
