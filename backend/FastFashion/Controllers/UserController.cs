using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using FastFashion.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace FastFashion.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {

        private readonly FashionItemContext _context;

        public UserController(
            FashionItemContext context)
        {
            _context = context;
        }

        // GET: api/FashionItems/5
        [HttpGet("{id}")]
        public async Task<ActionResult<User>> GetUser(int id)
        {
            var user = await _context.User.FindAsync(id);

            if (user == null)
            {
                return NotFound();
            }

            return user;
        }

        [HttpPost("register")]
        public async Task<ActionResult<User>> PostUserLogin(UserCreate user)
        {
            bool exists = await _context.User.AnyAsync(x => x.Username == user.Username);
            if (exists) { return BadRequest(new { message = "Username is already taken" }); }

            User item = new User
            {

                Username = user.Username,
                Password = user.Password

            };

            _context.User.Add(item);
            await _context.SaveChangesAsync();

            return this.Ok(new IdResult { Userid = item.UserId});
        }

        [HttpPost("login")]
        public async Task<ActionResult<User>> PostUserCreate(UserCreate paramUser)
        {
            
            bool result = await _context.User.AnyAsync(user => user.Username == paramUser.Username && user.Password == paramUser.Password);



            if (result)
            {
                User loginUser = await _context.User.FirstAsync(x => x.Username == paramUser.Username);
                IdResult response = new IdResult{ Userid = loginUser.UserId };
                return this.Ok(response);
            }
            else return BadRequest(new { message = "Username or password is incorrect" });
        }

    }
}
