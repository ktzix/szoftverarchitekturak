using Microsoft.EntityFrameworkCore.Migrations;

namespace FastFashion.Migrations
{
    public partial class useridNotFK : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems");

            migrationBuilder.DropIndex(
                name: "IX_FashionItems_UserId",
                table: "FashionItems");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_FashionItems_UserId",
                table: "FashionItems",
                column: "UserId");

            migrationBuilder.AddForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems",
                column: "UserId",
                principalTable: "User",
                principalColumn: "UserId",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
