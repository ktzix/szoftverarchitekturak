using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using FastFashion.Models;

namespace FastFashion.Controllers
{
    [Route("api/FashionItems")]
    [ApiController]
    public class FashionItemsController : ControllerBase
    {
        private readonly FashionItemContext _context;

        public FashionItemsController(FashionItemContext context)
        {
            _context = context;
        }

        // GET: api/FashionItems
        [HttpGet("all/{userId}")]
        public async Task<ActionResult<IEnumerable<FashionItem>>> GetFashionItems(int userId)
        {
            return await _context.FashionItems.Where(f => f.UserId == userId).ToListAsync();
        }

        // GET: api/FashionItems/5
        [HttpGet("{id}")]
        public async Task<ActionResult<FashionItem>> GetFashionItem(int id)
        {
            var fashionItem = await _context.FashionItems.FindAsync(id);

            if (fashionItem == null)
            {
                return NotFound();
            }

            return fashionItem;
        }

        // GET: api/FashionItems/type/csizma
        [HttpGet("type/{Type}/{UserId}")]
        public async Task<ActionResult<FashionItem>> GetFashionItemWithType(string type, int userId)
        {

            List<FashionItem> items =await _context.FashionItems.Where(f => f.UserId == userId).Where(item => item.Type == type).ToListAsync();

           if (items == null)
            {
                return NotFound();
            } 

            return this.Ok(items);
        }

        // GET: api/FashionItems/style/regi
        [HttpGet("style/{Style}/{UserId}")]
        public async Task<ActionResult<FashionItem>> GetFashionItemWithStyle(string style, int userId)
        {

            List<FashionItem> items = await _context.FashionItems.Where(f => f.UserId == userId ).Where(item => item.Style == style).ToListAsync();

            if (items == null)
            {
                return NotFound();
            }

            return this.Ok(items);
        }

        // PUT: api/FashionItems/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutFashionItem(int id, FashionItem fashionItem)
        {
            if (id != fashionItem.Id)
            {
                return BadRequest();
            }

            _context.Entry(fashionItem).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!FashionItemExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/FashionItems
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<FashionItem>> PostFashionItem(FashionItemCreate fashionItem)
        {
            bool exists = await _context.User.AnyAsync(x=> x.UserId == fashionItem.UserId);
            if (!exists) { return BadRequest(new { message = "User is not existing" }); }

            FashionItem item = new FashionItem
            {
                Style = fashionItem.Style,
                Detail = fashionItem.Detail,
                Type = fashionItem.Type,
                Date = fashionItem.Date,
                pictureUri = fashionItem.pictureUri,
                 UserId = fashionItem.UserId

            };

            _context.FashionItems.Add(item);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetFashionItem), new { id = item.Id }, item);
        }

        // DELETE: api/FashionItems/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<FashionItem>> DeleteFashionItem(int id)
        {
            var fashionItem = await _context.FashionItems.FindAsync(id);
            if (fashionItem == null)
            {
                return NotFound();
            }

            _context.FashionItems.Remove(fashionItem);
            await _context.SaveChangesAsync();

            return this.Ok();
        }

        private bool FashionItemExists(int id)
        {
            return _context.FashionItems.Any(e => e.Id == id);
        }
    }
}
